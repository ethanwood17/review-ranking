package com.ewood.reviewranking.model;

public class Review {

    private String title;
    private String date;
    private String stars;
    private String content;
    private String name;

    public long countExclamations() {
        return this.content.chars().filter(ch -> ch == '!').count();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Review{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", stars='" + stars + '\'' +
                ", content='" + content + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
