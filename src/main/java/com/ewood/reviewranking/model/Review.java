package com.ewood.reviewranking.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {

    private String date;
    private String content;
    private String name;

    public long countExclamations() {
        return this.content.chars().filter(ch -> ch == '!').count();
    }
}
