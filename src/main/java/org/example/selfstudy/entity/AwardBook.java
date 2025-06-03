package org.example.selfstudy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AwardBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//
//    @ManyToOne
//    @JoinColumn(name = "book_id")
//    private Book book;
//
//    @ManyToOne
//    @JoinColumn(name = "award_id")
//    private Award award;
//
//
    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonBackReference("book-awards")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "award_id")
    @JsonManagedReference("award-books")
    private Award award;

    private Integer year;

}
