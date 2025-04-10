package com.example.bookproject.repository;

import com.example.bookproject.entity.AuthorBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorBookRepository extends JpaRepository<AuthorBook, Long> {
}
