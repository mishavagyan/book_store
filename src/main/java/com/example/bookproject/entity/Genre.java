package com.example.bookproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;

    @Column(name = "genre", length = 100)
    private String name;

    @OneToMany(mappedBy = "genre")
    private Set<GenreBook> books = new HashSet<>();
}
