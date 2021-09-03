package com.ewood.reviewranking;

import com.ewood.reviewranking.model.Review;
import com.ewood.reviewranking.service.ParserService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class ParserServiceTests {


    File reviewWrapperTemplate = new File("src/test/resources/templates/review-entry.html");
    ParserService parserService = new ParserService();

    @Test
    void testParseReview() throws IOException {
        Review expectedReview = Review.builder()
                .date("August 25, 2021")
                .content("Best service department in east Texas. Patrick and the service department handle my appointment on time, with great courtesy and professionalism. Patrick remains one of the friendliest and professional service writers I've ever worked with.")
                .name("vnystel")
                .build();

        Document document = Jsoup.parse(reviewWrapperTemplate, "UTF-8", "");
        Element element = document.select(".review-entry").first();
        Review review = parserService.parseReview(element);
        assert review.equals(expectedReview);
    }

    @Test
    void testParsePage() throws IOException {
        Document document = Jsoup.parse(reviewWrapperTemplate, "UTF-8", "");
        Elements elements = document.select(".review-entry");
        assert elements.size() == 1;
    }

}
