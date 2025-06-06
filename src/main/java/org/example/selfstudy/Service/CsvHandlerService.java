package org.example.selfstudy.Service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.selfstudy.entity.*;
import org.example.selfstudy.entity.Character;
import org.example.selfstudy.gto.BookCsvDto;
import org.example.selfstudy.helper.HelperFunc;
import org.example.selfstudy.repository.*;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.Normalizer;
import java.util.*;

@Service
public class CsvHandlerService {
    private static final int BATCH_SIZE = 500; // Increased from 500
//    private static final int BATCH_SIZE = 250; // Increased from 500

    private final BookService bookService;
    private final AwardService awardService;

    private final BookRepository bookRepository;
    private final AwardRepository awardRepository;
    private final AwardBookRepository awardBookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorBookRepository authorBookRepository;
    private final CharacterRepository characterRepository;
    private final SettingRepository settingRepository;
    private final FormatRepository formatRepository;
    private final LanguageRepository languageRepository;
    private final GenreRepository genreRepository;
    private final GenreBookRepository genreBookRepository;

    List<BookCsvDto> bookCsvDtos = new ArrayList<>();

    @PersistenceContext
    private EntityManager entityManager;

    public CsvHandlerService(BookService bookService, AwardService awardService, BookRepository bookRepository, AwardRepository awardRepository, AwardBookRepository awardBookRepository, AuthorRepository authorRepository, AuthorBookRepository authorBookRepository, ClientHttpRequestFactorySettings clientHttpRequestFactorySettings, CharacterRepository characterRepository, SettingRepository settingRepository, FormatRepository formatRepository, LanguageRepository languageRepository, GenreRepository genreRepository, GenreBookRepository genreBookRepository) {
        this.bookService = bookService;
        this.awardService = awardService;
        this.bookRepository = bookRepository;
        this.awardRepository = awardRepository;
        this.awardBookRepository = awardBookRepository;
        this.authorRepository = authorRepository;
        this.authorBookRepository = authorBookRepository;
        this.characterRepository = characterRepository;
        this.settingRepository = settingRepository;
        this.formatRepository = formatRepository;
        this.languageRepository = languageRepository;
        this.genreRepository = genreRepository;
        this.genreBookRepository = genreBookRepository;
    }


    public List<BookCsvDto> handleUpload(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<BookCsvDto> csvToBean = new CsvToBeanBuilder<BookCsvDto>(reader)
                    .withType(BookCsvDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withThrowExceptions(false)
                    .build();

            List<BookCsvDto> validBooks = new ArrayList<>();

            int a = 1;
            for (Iterator<BookCsvDto> it = csvToBean.iterator(); it.hasNext(); ) {
                try {
                    validBooks.add(it.next());
                } catch (RuntimeException ex) {
//                    System.err.println("Skipping bad row: " + ex.getMessage() + " " + a);
                }
                a++;
            }

            bookCsvDtos = validBooks;
            return bookCsvDtos;

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV", e);
        }
    }


    public void some(List<BookCsvDto> dtos) {

        List<Book> books = new ArrayList<>();
        List<Award> awards = new ArrayList<>();
        List<AwardBook> awardBooks = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        List<AuthorBook> authorBooks = new ArrayList<>();
        List<Character> characterList = new ArrayList<>();
        List<Setting> settingList = new ArrayList<>();
//        Set<Format> formats = new HashSet<>();
        List<Format> formats = new ArrayList<>();
        List<Language> languages = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<GenreBook> genreBooks = new ArrayList<>();

        Map<String, Author> authorCache = new HashMap<>();
        Map<String, Award> awardCache = new HashMap<>();
        Map<String, Genre> genreCache = new HashMap<>();
        Map<String, Format> formatCache = new HashMap<>();
        Map<String, Language> languageCache = new HashMap<>();

        for (BookCsvDto dto : dtos) {
            Book book = bookService.convertToEntity(dto);
            books.add(book);

            // Handle Format
            String formatName = dto.getBookFormat();
            if (formatName != null && !formatName.isBlank()) {
                formatName = formatName.toUpperCase();
                if (formatCache.containsKey(formatName)) {
                    book.setFormat(formatCache.get(formatName));
                } else {
                    Format f = new Format();
                    f.setName(formatName);
                    formatCache.put(formatName, f);
                    formats.add(f);
                    book.setFormat(f);
                }
            }

            // Handle Language
            String languageName = dto.getLanguage();
            if (languageName != null && !languageName.isBlank()) {
                languageName = languageName.toUpperCase();
                if (languageCache.containsKey(languageName)) {
                    book.setLanguage(languageCache.get(languageName));
                } else {
                    Language l = new Language();
                    l.setName(languageName);
                    languageCache.put(languageName, l);
                    languages.add(l);
                    book.setLanguage(l);
                }
            }

            // Handle Awards
            for (String raw : HelperFunc.getList(dto.getAwards())) {
                for (Map.Entry<String, Integer> entry : HelperFunc.parseAwards(raw)) {
                    String name = entry.getKey();
                    Integer year = entry.getValue();

                    if (name == null || name.isBlank()) continue;

                    Award award = awardCache.computeIfAbsent(name, k -> {
                        Award newAward = new Award();
                        newAward.setName(k);
                        awards.add(newAward);
                        return newAward;
                    });

                    AwardBook ab = new AwardBook();
                    ab.setAward(award);
                    ab.setBook(book);
                    ab.setYear(year);

                    book.getAwards().add(ab);
                    award.getBooks().add(ab);
                    awardBooks.add(ab);
                }
            }

            // Handle Authors
            for (String name : HelperFunc.splitByCommas(dto.getAuthor())) {
                if (name == null || name.isBlank()) continue;

                Author author = authorCache.computeIfAbsent(name, k -> {
                    Author newAuthor = new Author();
                    newAuthor.setName(k);
                    authors.add(newAuthor);
                    return newAuthor;
                });

                AuthorBook ab = new AuthorBook();
                ab.setAuthor(author);
                ab.setBook(book);

                book.getAuthors().add(ab);
                author.getBooks().add(ab);
                authorBooks.add(ab);
            }

            // Handle Genres
            for (String s : HelperFunc.getList(dto.getGenres())) {
                if (s == null || s.isBlank()) continue;

                Genre genre = genreCache.computeIfAbsent(s, k-> {
                    Genre newGenre = new Genre();
                    newGenre.setName(k);
                    genres.add(newGenre);
                    return newGenre;
                });

                GenreBook gb = new GenreBook();
                gb.setGenre(genre);
                gb.setBook(book);

                book.getGenres().add(gb);
                genre.getBooks().add(gb);
                genreBooks.add(gb);
            }

            // Handle Settings
            for (String s : HelperFunc.getList(dto.getSetting())) {
                if (s == null || s.isBlank()) continue;

                Setting setting = new Setting();
                setting.setName(s);
                setting.setBook(book);
                settingList.add(setting);
                book.getSettings().add(setting);
            }

            // Handle Characters
            for (String s : HelperFunc.getList(dto.getCharacters())) {
                if (s == null || s.isBlank()) continue;

                Character character = new Character();
                character.setName(s);
                character.setBook(book);
                characterList.add(character);
                book.getCharacters().add(character);
            }

            if (books.size() >= BATCH_SIZE) {
                saveBatch(
                        books, awards, awardBooks,
                        authors, authorBooks, settingList,
                        characterList, formats, languages,
                        genres, genreBooks);

                books.clear();
                awards.clear();
                awardBooks.clear();
                authors.clear();
                authorBooks.clear();
                genreBooks.clear();
                genres.clear();
                settingList.clear();
                characterList.clear();
                formats.clear();
                languages.clear();
            }
        }

        saveBatch(
                books, awards, awardBooks,
                authors, authorBooks, settingList,
                characterList, formats, languages,
                genres, genreBooks);
    }

    @Transactional
    protected void saveBatch(List<Book> books, List<Award> awards, List<AwardBook> awardBooks,
                             List<Author> authors, List<AuthorBook> authorBooks, List<Setting> settings,
                             List<Character> characters, List<Format> formats, List<Language> languages,
                             List<Genre> genres, List<GenreBook> genreBooks) {

        awardRepository.saveAll(awards);
        authorRepository.saveAll(authors);
        genreRepository.saveAll(genres);
        formatRepository.saveAll(formats);
        languageRepository.saveAll(languages);
        bookRepository.saveAll(books);
        awardBookRepository.saveAll(awardBooks);
        authorBookRepository.saveAll(authorBooks);
        genreBookRepository.saveAll(genreBooks);
        settingRepository.saveAll(settings);
        characterRepository.saveAll(characters);

        entityManager.clear();
    }


    public List<BookCsvDto> getData() {
        return bookCsvDtos == null ? new ArrayList<>() : bookCsvDtos;
    }
}
