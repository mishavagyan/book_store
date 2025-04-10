package com.example.bookproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "books", schema = "public")
public class Book {
    @Id
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "title", nullable = false, length = 500)
    private String title;

    @Column(name = "rating", precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "isbn", unique = true, length = 20)
    private String isbn;

    @Column(name = "edition", length = 100)
    private String edition;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @Column(name = "first_publish_date")
    private LocalDate firstPublishDate;

    @Column(name = "num_ratings")
    private Integer numRatings;

    @Column(name = "five_stars")
    private Integer fiveStars;

    @Column(name = "four_stars")
    private Integer fourStars;

    @Column(name = "three_stars")
    private Integer threeStars;

    @Column(name = "two_stars")
    private Integer twoStars;

    @Column(name = "one_stars")
    private Integer oneStars;

    @Column(name = "liked_percent")
    private Integer likedPercent;

    @Column(name = "cover_img")
    private String coverImg;

    @Column(name = "bbe_score")
    private Integer bbeScore;

    @Column(name = "bbe_votes")
    private Integer bbeVotes;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_format_id")
    private BookFormat bookFormat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id", foreignKey = @ForeignKey(name = "fk_book_series"))
    @JsonBackReference
    private Series series;

    @OneToMany(mappedBy = "book")
    private Set<AuthorBook> authors = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<AwardBook> awards = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<GenreBook> genres = new HashSet<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Setting> settings = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;


}






//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "book_authors"
//            name = "book_authors",
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "author_id"),
//            foreignKey = @ForeignKey(name = "fk_book_author"),
//            inverseForeignKey = @ForeignKey(name = "fk_author_book"))
//    )
//    private Set<Author> authors = new HashSet<>();

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "book_genres",
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "genre_id"))
//    private Set<Genre> genres = new HashSet<>();
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "book_characters",
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "character_id"))
//    private Set<Character> characters = new HashSet<>();
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "publisher_id", foreignKey = @ForeignKey(name = "fk_book_publisher"))
//    private Publisher publisher;
//
//    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<BookAward> awards = new HashSet<>();

//    @ElementCollection
//    @CollectionTable(name = "book_ratings_distribution",
//            joinColumns = @JoinColumn(name = "book_id"))
//    @MapKeyColumn(name = "star_rating")
//    @Column(name = "count")
//    private Map<Integer, Integer> ratingsByStars = new HashMap<>();
//
//    @Column(name = "setting", length = 200)
//    private String setting;


//}
