package com.example.bookproject.controller;

import com.example.bookproject.entity.Book;
import com.example.bookproject.entity.Series;
import com.example.bookproject.repository.BookRepository;
import com.example.bookproject.repository.SeriesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;
    private final SeriesRepository seriesRepository;

    public BookController(BookRepository bookRepository,
                          SeriesRepository seriesRepository) {
        this.bookRepository = bookRepository;
        this.seriesRepository = seriesRepository;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/by-series/{seriesName}")
    public List<Book> getBooksBySeries(@PathVariable String seriesName) {
        return bookRepository.findBySeriesName(seriesName);
    }

    @GetMapping("/series")
    public List<Series> getAllSeries() {
        return seriesRepository.findAll();
    }
}
