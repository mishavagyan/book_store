package org.example.selfstudy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuthorBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonBackReference("book-authors")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonManagedReference("author-books")
    private Author author;

//    @ManyToOne
//    @JoinColumn(name = "author_id")
//    private Author author;
//
//    @ManyToOne
//    @JoinColumn(name = "book_id")
//    private Book book;
}
