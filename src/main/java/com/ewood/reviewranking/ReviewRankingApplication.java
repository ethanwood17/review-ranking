package com.ewood.reviewranking;

import com.ewood.reviewranking.model.Review;
import com.ewood.reviewranking.service.ScraperService;
import picocli.CommandLine;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@CommandLine.Command(
)
public class ReviewRankingApplication implements Runnable {

    @CommandLine.Option(names = "-n", defaultValue = "50", description = "The number of reviews you want to parse.")
    int number;
    @CommandLine.Option(names = "-l", defaultValue = "3", description = "The number of reviews you want to output to stdout.")
    int limit;

    public static void main(String[] args) {
        CommandLine.run(new ReviewRankingApplication(), args);
    }

    @Override
    public void run() {
        System.out.println("Beginning scraping!");
        ScraperService scraperService = new ScraperService();
        List<Review> sortedReviews = scraperService.scrapeReviews(number)
                .stream()
                .sorted(Comparator.comparingLong(Review::countExclamations).reversed())
                .limit(limit)
                .collect(Collectors.toList());

        for (int i = 0; i < sortedReviews.size(); i++) {
            System.out.println(i + 1 + " : " + sortedReviews.get(i));
        }
    }
}
