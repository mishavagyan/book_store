package org.example.selfstudy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class GenreBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonBackReference("book-genres")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    @JsonBackReference("genre-books")
    private Genre genre;
}
