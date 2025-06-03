package org.example.selfstudy.repository;

import org.example.selfstudy.entity.AuthorBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorBookRepository extends JpaRepository<AuthorBook, Integer> {
}
