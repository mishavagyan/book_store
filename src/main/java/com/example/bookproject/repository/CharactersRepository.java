package com.example.bookproject.repository;

import com.example.bookproject.entity.Characters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharactersRepository extends JpaRepository<Characters, Long> {
}
