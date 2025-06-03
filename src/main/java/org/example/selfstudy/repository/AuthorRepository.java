package org.example.selfstudy.repository;

import org.example.selfstudy.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByNameIn(Collection<String> names);
}
