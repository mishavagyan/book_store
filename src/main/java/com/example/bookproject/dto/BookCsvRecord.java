package com.example.bookproject.dto;

import lombok.Data;

@Data
public class BookCsvRecord {
    private String bookId;
    private String title;
    private String series;
    private String author;
    private String rating;
    private String description;
    private String language;
    private String isbn;
    private String genres;
    private String characters;
    private String bookFormat;
    private String edition;
    private String pages;
    private String publisher;
    private String publishDate;
    private String firstPublishDate;
    private String awards;
    private String numRatings;
    private String ratingsByStars;
    private String likedPercent;
    private String setting;
    private String coverImg;
    private String bbeScore;
    private String bbeVotes;
    private String price;
}