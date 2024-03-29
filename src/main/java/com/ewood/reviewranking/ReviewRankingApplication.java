package com.ewood.reviewranking;

import com.ewood.reviewranking.model.Review;
import com.ewood.reviewranking.service.RankingService;
import com.ewood.reviewranking.service.ScraperService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CommandLine.Command
public class ReviewRankingApplication implements Runnable {

    @CommandLine.Option(names = "-n", defaultValue = "50", description = "The number of reviews you want to parse.")
    int number;
    @CommandLine.Option(names = "-l", defaultValue = "3", description = "The number of reviews you want to output to stdout.")
    int limit;

    String url = "https://www.dealerrater.com/dealer/McKaig-Chevrolet-Buick-A-Dealer-For-The-People-dealer-reviews-23685/";

    public static void main(String[] args) {
        CommandLine.run(new ReviewRankingApplication(), args);
    }

    @Override
    public void run() {
        ScraperService scraperService = new ScraperService(url);
        RankingService rankingService = new RankingService();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Review> sortedReviews = scraperService.scrapeReviews(number)
                .stream()
                .sorted(Comparator.comparingLong(rankingService::compareExclamationCount).reversed())
                .sorted(Comparator.comparingLong(rankingService::compareContentLength).reversed())
                .limit(limit)
                .collect(Collectors.toList());

        try {
            System.out.println(objectMapper.writeValueAsString(sortedReviews));
        } catch (JsonProcessingException e) {
            System.out.println("Problem with JSON conversion.");
        }
    }
}
