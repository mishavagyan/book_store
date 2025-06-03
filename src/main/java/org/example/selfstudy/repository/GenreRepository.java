package org.example.selfstudy.repository;

import org.example.selfstudy.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findByNameIn(Collection<String> names);
}
