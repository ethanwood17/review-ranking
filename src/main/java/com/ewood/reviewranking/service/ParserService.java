package com.ewood.reviewranking.service;

import com.ewood.reviewranking.model.Review;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.stream.Stream;

public class ParserService {

    public Stream<Review> parsePage(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("Can't access website!");
        }
        if (doc != null) {
            Elements elements = doc.select(".review-entry");
            return elements.stream().parallel().map(this::parseReview);
        } else return Stream.empty();
    }

    public Review parseReview(Element element) {
        return Review.builder()
                .name(extractName(element))
                .content(extractContent(element))
                .date(extractDate(element))
                .build();
    }

    private String extractName(Element element) {
        Elements reviewWrapper = element.select(".review-wrapper");
        return reviewWrapper.first().select(".italic").text().replace("-", "").trim();
    }

    private String extractContent(Element element) {
        Elements reviewWrapper = element.select(".review-wrapper");
        return reviewWrapper.select(".review-content").text().trim();
    }

    private String extractDate(Element element) {
        Elements dateContainer = element.select(".review-date");
        return dateContainer.select(".italic").text().trim();
    }
}
