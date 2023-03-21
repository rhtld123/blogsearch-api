package com.example.blogsearch.modules.blogsearch.application.port.in.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BlogSearchResponse {
    private int totalCount;
    private int numberOfElements;
    private int pageableCount;
    private boolean isEnd;
    private List<BlogContent> contents;

    private BlogSearchResponse(int totalCount, int numberOfElements, int pageableCount, boolean isEnd, List<BlogContent> contents) {
        this.totalCount = totalCount;
        this.numberOfElements = numberOfElements;
        this.pageableCount = pageableCount;
        this.isEnd = isEnd;
        this.contents = contents;
    }

    public static BlogSearchResponse of(int totalCount, int numberOfElements, int pageableCount, boolean isEnd, List<BlogContent> contents) {
        return new BlogSearchResponse(totalCount, numberOfElements, pageableCount, isEnd, contents);
    }

    @Getter
    public static class BlogContent {
        private String title;
        private String contents;
        private String url;
        private String blogName;
        private String thumbnail;
        private LocalDateTime dateTime;

        private BlogContent(String title, String contents, String url, String blogName, String thumbnail, LocalDateTime dateTime) {
            this.title = title;
            this.contents = contents;
            this.url = url;
            this.blogName = blogName;
            this.thumbnail = thumbnail;
            this.dateTime = dateTime;
        }

        public static BlogContent of(String title, String contents, String url, String blogName, String thumbnail, LocalDateTime dateTime) {
            return new BlogContent(title, contents, url, blogName, thumbnail, dateTime);
        }
    }
}