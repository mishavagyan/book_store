package org.example.selfstudy.repository;

import org.example.selfstudy.entity.GenreBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreBookRepository extends JpaRepository<GenreBook, Integer> {
}
