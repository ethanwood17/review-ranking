package com.ewood.reviewranking;

import com.ewood.reviewranking.model.Review;
import com.ewood.reviewranking.service.ScraperService;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewRankingApplication {

    public static void main(String[] args) throws IOException {
        ScraperService scraperService = new ScraperService();
        List<Review> sortedReviews = scraperService.scrapeReviews()
                .stream()
                .sorted(Comparator.comparingLong(Review::countExclamations).reversed())
                .limit(3)
                .collect(Collectors.toList());

        for (int i = 0; i < sortedReviews.size(); i++) {
            System.out.println(i + 1 + " : " + sortedReviews.get(i));
        }
    }
}
