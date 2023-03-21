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
    private List<items> items;

    @Getter
    @NoArgsConstructor
    public static class items {
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
    }
}