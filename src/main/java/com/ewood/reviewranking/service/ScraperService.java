package com.ewood.reviewranking.service;

import com.ewood.reviewranking.model.Review;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScraperService {

    private static final String BASE_URL = "https://www.dealerrater.com/dealer/McKaig-Chevrolet-Buick-A-Dealer-For-The-People-dealer-reviews-23685/";

    public List<Review> scrapeReviews() throws IOException {
        List<Review> reviews = new ArrayList<>();
        int offset = 0;
        String currentUrl = BASE_URL;
        while(reviews.size() < 50) {
            Document doc = Jsoup.connect(currentUrl).get();
            Elements elements = doc.select(".review-entry");
            offset++;
            for (Element element : elements) {
                reviews.add(this.parse(element));
            }
            currentUrl = updatePage(offset);
        }
        return reviews;
    }

    private String updatePage(int offset) {
        return BASE_URL + "page" + ++offset;
    }

    public Review parse(Element element) {
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
