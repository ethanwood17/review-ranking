package com.ewood.reviewranking;

import com.ewood.reviewranking.model.Review;
import com.ewood.reviewranking.service.ScraperService;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ScraperServiceTests {

    private final String url = "https://www.dealerrater.com/dealer/McKaig-Chevrolet-Buick-A-Dealer-For-The-People-dealer-reviews-23685/";
    ScraperService scraperService = new ScraperService(url);

    @Test
    void testScrapeReviews() {
        List<Review> reviewList = scraperService.scrapeReviews(1);
        assert reviewList.size() == 1;

    }
}
