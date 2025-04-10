package com.example.bookproject.repository;

import com.example.bookproject.entity.GenreBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreBookRepository extends JpaRepository<GenreBook, Long> {
}
