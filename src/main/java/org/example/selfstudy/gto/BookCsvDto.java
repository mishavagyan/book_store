package org.example.selfstudy.gto;

import com.opencsv.bean.CsvBindByName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCsvDto {

    @CsvBindByName(column = "title")
    private String title;

    @CsvBindByName(column = "series")
    private String series;

    @CsvBindByName(column = "author")
    private String author;

    @CsvBindByName(column = "rating")
    private String rating;

    @CsvBindByName(column = "description")
    private String description;

    @CsvBindByName(column = "language")
    private String language;

    @CsvBindByName(column = "isbn")
    private String isbn;

    @CsvBindByName(column = "genres")
    private String genres;

    @CsvBindByName(column = "characters")
    private String characters;

    @CsvBindByName(column = "bookFormat")
    private String bookFormat;

    @CsvBindByName(column = "edition")
    private String edition;

    @CsvBindByName(column = "pages")
    private String pages;

    @CsvBindByName(column = "publisher")
    private String publisher;

    @CsvBindByName(column = "publishDate")
    private String publishDate;

    @CsvBindByName(column = "firstPublishDate")
    private String firstPublishDate;

    @CsvBindByName(column = "awards")
    private String awards;

    @CsvBindByName(column = "numRatings")
    private String numRatings;

    @CsvBindByName(column = "ratingsByStars")
    private String ratingsByStars;

    @CsvBindByName(column = "likedPercent")
    private String likedPercent;

    @CsvBindByName(column = "setting")
    private String setting;

    @CsvBindByName(column = "coverImg")
    private String coverImg;

    @CsvBindByName(column = "bbeScore")
    private String bbeScore;

    @CsvBindByName(column = "bbeVotes")
    private String bbeVotes;

    @CsvBindByName(column = "price")
    private String price;
}