package com.example.bookproject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "series")
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seriesId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "seriesNumber")
    private String seriesNumber;

    @OneToMany(mappedBy = "series")
    @JsonManagedReference
    private Set<Book> books = new HashSet<>();
}
