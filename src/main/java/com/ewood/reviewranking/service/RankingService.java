package com.ewood.reviewranking.service;

import com.ewood.reviewranking.model.Review;

/**
 * Ranks reviews in order from most to least fake
 */
public class RankingService {

    /**
     * Reviews with more exclamation points seem more likely to be fake, at least to me
     * @param review
     * @return
     */
    public long compareExclamationCount(Review review) {
        return review.getContent().chars().filter(ch -> ch == '!').count();
    }

    /**
     * Longer reviews also feel less genuine than simple ones
     * @param review
     * @return
     */
    public int compareContentLength(Review review) {
        return review.getContent().length();
    }
}
