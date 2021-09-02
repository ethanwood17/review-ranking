package com.ewood.reviewranking.service;

import com.ewood.reviewranking.model.Review;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ScraperService {

    private static final String BASE_URL = "https://www.dealerrater.com/dealer/McKaig-Chevrolet-Buick-A-Dealer-For-The-People-dealer-reviews-23685/";
    private static final int pageSize = 10;

    ParserService parserService = new ParserService();

    public List<Review> scrapeReviews(int number) {
        List<Review> reviews = new ArrayList<>();
        int pages = getPages(number);
        Stream.iterate(pages, n -> n + 1)
                .limit(pages)
                .parallel()
                .forEach(i -> parsePage(i).forEachOrdered(reviews::add));
        return reviews;
    }

    private Stream<Review> parsePage(int pageNumber) {
        String currentUrl = this.getUrlForPage(pageNumber);
        Document doc = null;
        try {
            doc = Jsoup.connect(currentUrl).get();
        } catch (IOException e) {
            System.out.println("Can't access website!");
        }
        if (doc != null) {
            Elements elements = doc.select(".review-entry");
            return elements.stream().parallel().map(parserService::parseReview);
        }
        else return Stream.empty();
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
        return BASE_URL + "page" + pageNumber;
    }




}
