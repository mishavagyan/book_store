//package org.example.selfstudy.controller;
//
//import org.example.selfstudy.Service.*;
//import org.example.selfstudy.entity.Award;
//import org.example.selfstudy.entity.AwardBook;
//import org.example.selfstudy.entity.Book;
//import org.example.selfstudy.entity.Language;
//import org.example.selfstudy.gto.BookCsvDto;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//@RestController
//@RequestMapping("/api/books")
//public class BookController {
//
//    private final CsvHandlerService csvHandlerService;
//    private final BookService bookService;
//    private final AwardService awardService;
//    private final AwardBookService awardBookService;
//    private final LanguageService languageService;
//
//    public BookController(CsvHandlerService csvHandlerService, BookService bookService, AwardService awardService, AwardBookService awardBookService, LanguageService languageService) {
//        this.csvHandlerService = csvHandlerService;
//        this.bookService = bookService;
//        this.awardService = awardService;
//        this.awardBookService = awardBookService;
//        this.languageService = languageService;
//    }
//
//    @GetMapping
//    public ResponseEntity<?> getBooks() {
//        List<Book> books = bookService.getBooks(csvHandlerService.getData());
//        if (books.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no data");
//        }
//        return ResponseEntity.ok(books);
//    }
//
//    @GetMapping("/awards")
//    public ResponseEntity<?> getAwards() {
//        Set<Award> awards = awardService.getAwards();
//        if (awards.isEmpty())
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no data");
//        return ResponseEntity.ok(awards);
//    }
//
//    @GetMapping("/awardBook")
//    public ResponseEntity<?> getAwardBook() {
//        List<BookCsvDto> dtos = csvHandlerService.getData();
//        List<Book> books = bookService.getBooks(dtos);
//
//        List<AwardBook> awardBooks = awardBookService.linkAwardBooks(
//                dtos,
//                books,
//                awardService.getAwardByNameMap());
//        if (awardBooks.isEmpty())
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no data");
//        return ResponseEntity.ok(awardBooks);
//    }
//
//    @GetMapping("/languages")
//    public ResponseEntity<?> getLanguages() {
//        Collection<Language> allLanguages = languageService.getAllLanguages();
//        if (allLanguages.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no data");
//        }
//        return ResponseEntity.ok(allLanguages);
//    }
//
//}
