package com.example.blogsearch.modules.blogsearch.application.port.out.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BlogSearchNaverResponse {

    @JsonProperty("lastBuildDate")
    private String lastBuildDate;
    @JsonProperty("total")
    private Long total;
    @JsonProperty("start")
    private Long start;
    @JsonProperty("display")
    private Long display;
    @JsonProperty("items")
    private List<item> items;

    private BlogSearchNaverResponse(String lastBuildDate, Long total, Long start, Long display, List<item> items) {
        this.lastBuildDate = lastBuildDate;
        this.total = total;
        this.start = start;
        this.display = display;
        this.items = items;
    }

    public static BlogSearchNaverResponse of(String lastBuildDate, Long total, Long start, Long display, List<item> item) {
        return new BlogSearchNaverResponse(lastBuildDate, total, start, display, item);
    }

    @Getter
    @NoArgsConstructor
    public static class item {
        @JsonProperty("title")
        private String title;
        @JsonProperty("link")
        private String link;
        @JsonProperty("description")
        private String description;
        @JsonProperty("bloggername")
        private String bloggername;
        @JsonProperty("bloggerlink")
        private String bloggerlink;
        @JsonProperty("postdate")
        private String postdate;

        private item(String title, String link, String description, String bloggername, String bloggerlink, String postdate) {
            this.title = title;
            this.link = link;
            this.description = description;
            this.bloggername = bloggername;
            this.bloggerlink = bloggerlink;
            this.postdate = postdate;
        }

        public static item of(String title, String link, String description, String bloggername, String bloggerlink, String postdate) {
            return new item(title, link, description, bloggername, bloggerlink, postdate);
        }
    }
}