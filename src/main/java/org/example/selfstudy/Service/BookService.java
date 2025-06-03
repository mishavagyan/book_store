/*

//package org.example.selfstudy.Service;
//
//import org.example.selfstudy.entity.Book;
//import org.example.selfstudy.gto.BookCsvDto;
//import org.example.selfstudy.repository.BookRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicLong;
//
//@Service
//public class BookService {
////    private final FormatService formatService;
////    private final LanguageService languageService;
////    private final BookRepository bookRepository;
////
////    public BookService(FormatService formatService, LanguageService languageService, BookRepository bookRepository) {
////        this.formatService = formatService;
////        this.languageService = languageService;
////        this.bookRepository = bookRepository;
////    }
////    List<Book> books = new ArrayList<>();
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    public Book convertToEntity(BookCsvDto dto) {
//        Book book = new Book();
//        book.setTitle(dto.getTitle());
//        book.setDescription(dto.getDescription());
//        book.setIsbn(dto.getIsbn());
//        book.setPages(Integer.parseInt(dto.getPages()));
//        book.setPublishDate(dto.getPublishDate());
//        book.setFirstPublishDate(dto.getFirstPublishDate());
//        book.setNumRatings(dto.getNumRatings());
//        book.setRatingsByStars(dto.getRatingsByStars());
//        book.setLikedPercent(dto.getLikedPercent());
//        book.setCoverImg(dto.getCoverImg());
//        book.setPrice(dto.getPrice());
//        book.setBbeScore(dto.getBbeScore());
//        book.setBbeVotes(dto.getBbeVotes());
////        book.setFormat(formatService.getOrCreate(dto.getBookFormat()));
////        book.setFormatId(formatService.getOrCreate(dto.getBookFormat()).getId());
////        book.setLanguage(languageService.getOrCreate(dto.getLanguage()).getId());
//        return book;
//    }
//
//    public List<Book> getBooks(List<BookCsvDto> dtos) {
////        AtomicLong idGenerator = new AtomicLong(1);
//
////        return dtos.stream().map(dto -> {
////            Book book = convertToEntity(dto);
////            book.setBookId((int) idGenerator.getAndIncrement());
////            return book;
////        }).toList();
//        List<Book> books = dtos.stream().map(dto -> convertToEntity(dto)).toList();
//        return books;
//    }
//
//    public void saveBook(List<BookCsvDto> dtos) {
//        bookRepository.saveAll(getBooks(dtos));
//    }
//
//}

 */


package org.example.selfstudy.Service;

import org.example.selfstudy.entity.Book;
import org.example.selfstudy.entity.Character;
import org.example.selfstudy.entity.Setting;
import org.example.selfstudy.gto.BookCsvDto;
import org.example.selfstudy.helper.HelperFunc;
import org.example.selfstudy.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book convertToEntity(BookCsvDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setIsbn(dto.getIsbn());
//        String pages = ;

        try {
            book.setPages(HelperFunc.extractNumber(dto.getPages()));
//            book.setPages(Integer.parseInt(dto.getPages()));
        } catch (Exception e) {
            book.setPages(0);
//            System.out.println(e.getMessage() + " " + dto.getTitle());
        }
        book.setPublishDate(dto.getPublishDate());
        book.setFirstPublishDate(dto.getFirstPublishDate());
        try {
            book.setNumRatings(Long.parseLong(dto.getNumRatings()));
        } catch (Exception e) {
//            System.out.println(e.getMessage() + " " + dto.getTitle() + "\nCannot parse long numRating");
        }
        book.setRatingsByStars(dto.getRatingsByStars());
        try {
            byte b = Byte.parseByte(dto.getLikedPercent());
            if (b >= 0 && b <= 100)
                book.setLikedPercent(b);
            else {
                book.setLikedPercent((byte) 0);
//                System.out.println(dto.getTitle() + " : percent should be <= 100 and >= 0 : percent = " + dto.getLikedPercent());
            }
        } catch (Exception e) {
            book.setLikedPercent((byte) 0);
//            System.out.println(e.getMessage() + " " + dto.getTitle() + "\nCannot parse liked percent : " + dto.getLikedPercent());
        }
        book.setCoverImg(dto.getCoverImg());
        book.setPrice(dto.getPrice());
        try {
            book.setBbeScore(Long.parseLong(dto.getBbeScore()));
        } catch (Exception e) {
//            System.out.println(e.getMessage() + " " + dto.getTitle() + "\nCannot parse bbeScore");
        }
        try {
            book.setBbeVotes(Long.parseLong(dto.getBbeVotes()));
        } catch (Exception e) {
//            System.out.println(e.getMessage() + " " + dto.getTitle() + "\nCannot parse bbeVotes");
        }

//        try {
//            List<String> settings = HelperFunc.getList(dto.getSetting());
//            for (String setting : settings) {
//                Setting setting1 = new Setting();
//                setting1.setName(setting);
//                setting1.setBook(book);
//                book.getSettings().add(setting1);
//            }
//        } catch (Exception e) {}

//        try {
//            List<String> characters = HelperFunc.getList(dto.getCharacters());
//            for (String character : characters) {
//                Character character1 = new Character();
//                character1.setName(character);
//                character1.setBook(book);
//                book.getCharacters().add(character1);
//            }
//
//        } catch (Exception e) {}

        return book;
    }

    public List<Book> getBooks(List<BookCsvDto> dtos) {
        List<Book> books = dtos.stream().map(dto -> convertToEntity(dto)).toList();
        return books;
    }

    public void saveBook(List<BookCsvDto> dtos) {
        List<Book> books = dtos.stream().map(dto -> convertToEntity(dto)).toList();

        bookRepository.saveAll(books);
    }

    public void saveBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

}
