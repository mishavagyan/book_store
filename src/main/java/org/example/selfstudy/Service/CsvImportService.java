//package org.example.selfstudy.Service;
//
//import org.example.selfstudy.entity.Book;
//import org.example.selfstudy.gto.BookCsvDto;
//import org.example.selfstudy.repository.BookRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
////@Service
//public class CsvImportService {
//
//    private final BookRepository bookRepository;
//
//    public CsvImportService(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
//
//    public void importBooks(List<BookCsvDto> dtoList) {
//        List<Book> books = dtoList.stream()
//                .map(dto -> Book.builder()
//                        .bookId(dto.getBookId())
//                        .title(dto.getTitle())
//                        .isbn(dto.getIsbn())
//                        .series(dto.getSeries())
//                        .build())
//                .toList();
//
//        bookRepository.saveAll(books);
//    }
//}
