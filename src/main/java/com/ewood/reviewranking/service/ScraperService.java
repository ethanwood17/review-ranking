package com.ewood.reviewranking.service;

import com.ewood.reviewranking.model.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ScraperService {

    private final String baseUrl;
    private static final int pageSize = 10;

    ParserService parserService = new ParserService();

    public ScraperService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<Review> scrapeReviews(int number) {
        List<Review> reviews = new ArrayList<>();
        int pages = getPages(number);
        Stream.iterate(pages, n -> n + 1)
                .limit(pages)
                .parallel()
                .forEach(i -> parserService.parsePage(getUrlForPage(i))
                        .limit(number)
                        .forEachOrdered(reviews::add)
                );
        return reviews;
    }

    private int getPages(int number) {
        int pages = number / pageSize;
        int rem = number % pageSize;
        if (rem != 0) {
            pages++;
        }
        return pages;
    }

    private String getUrlForPage(int pageNumber) {
        return baseUrl + "page" + pageNumber;
    }


}
