package com.example.bookproject.repository;

import com.example.bookproject.entity.BookFormat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookFormatRepository extends JpaRepository<BookFormat, Long> {
}
