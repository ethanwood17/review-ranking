package com.ewood.reviewranking.service;

import com.ewood.reviewranking.model.Review;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.stream.Stream;

public class ParserService {



    public Review parseReview(Element element) {
        Review review = new Review();
        review.setName(this.extractName(element));
        review.setContent(this.extractContent(element));
        return review;
    }

    private String extractName(Element element) {
        Elements reviewWrapper = element.select(".review-wrapper");
        return reviewWrapper.first().select("span").select(".notranslate").text().replace("-", "").trim();
    }

    private String extractContent(Element element) {
        Elements reviewWrapper = element.select(".review-wrapper");
        return reviewWrapper.select(".review-content").text().trim();
    }
}
