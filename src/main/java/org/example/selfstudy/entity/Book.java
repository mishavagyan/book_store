//package org.example.selfstudy.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
////@Entity
//@Getter @Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Book {
//
//    @Id
//    private int bookId;
//
//    private String title;
//
//    private String isbn;
//
//    private String series;
//}


package org.example.selfstudy.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.yaml.snakeyaml.tokens.FlowSequenceEndToken;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "books", schema = "public")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String isbn;
    private Integer pages;
    private String publishDate;
    private String firstPublishDate;
    private Long numRatings;
    private String ratingsByStars;
    private Byte likedPercent;
    private String coverImg;
    private Long bbeScore;
    private Long bbeVotes;
    private String price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Format format;

    @ManyToOne(fetch = FetchType.LAZY)
    private Language language;

    @OneToMany(mappedBy = "book")
    @JsonManagedReference("book-awards")
    private Set<AwardBook> awards = new HashSet<>();

    @OneToMany(mappedBy = "book")
    @JsonManagedReference("book-authors")
    private Set<AuthorBook> authors = new HashSet<>();

//    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "book")
    @JsonManagedReference
    private List<Setting> settings = new ArrayList<>();

    @OneToMany(mappedBy = "book")
//    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Character> characters = new ArrayList<>();
}