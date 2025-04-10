package com.example.bookproject.repository;

import com.example.bookproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBySeriesName(String seriesName);
}