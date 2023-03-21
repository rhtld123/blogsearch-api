package com.example.blogsearch.modules.blogsearch.application.port.in.model;

import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class BlogSearchResponse<T> {
    private Long totalCount;
    private int numberOfElements;
    private List<T> contents;
    private String searchPlatform;

    private BlogSearchResponse(Long totalCount, int numberOfElements, List<T> contents, String searchPlatform) {
        this.totalCount = totalCount;
        this.numberOfElements = numberOfElements;
        this.contents = contents;
        this.searchPlatform = searchPlatform;
    }

    public static <T> BlogSearchResponse<T> of(Long totalCount, int numberOfElements, List<T> contents, String searchPlatform) {
        return new BlogSearchResponse(totalCount, numberOfElements, contents, searchPlatform);
    }

    @Getter
    public static class BlogKakaoContent {
        private String title;
        private String contents;
        private String url;
        private String blogName;
        private String thumbnail;
        private LocalDateTime dateTime;

        private BlogKakaoContent(String title, String contents, String url, String blogName, String thumbnail, String dateTime) {
            this.title = title;
            this.contents = contents;
            this.url = url;
            this.blogName = blogName;
            this.thumbnail = thumbnail;
            this.dateTime = convertDatetime(dateTime);
        }

        public static BlogKakaoContent of(String title, String contents, String url, String blogName, String thumbnail, String dateTime) {
            return new BlogKakaoContent(title, contents, url, blogName, thumbnail, dateTime);
        }

        private LocalDateTime convertDatetime(String datetime) {
            return LocalDateTime.from(Instant.from(DateTimeFormatter.ISO_DATE_TIME.parse(datetime)).atZone(ZoneId.of("Asia/Seoul")));
        }

    }

    @Getter
    public static class BlogNaverContent {
        private String title;
        private String link;
        private String description;
        private String bloggername;
        private String bloggerlink;
        private LocalDate postdate;

        private BlogNaverContent(String title, String link, String description, String bloggername, String bloggerlink, String postdate) {
            this.title = title;
            this.link = link;
            this.description = description;
            this.bloggername = bloggername;
            this.bloggerlink = bloggerlink;
            this.postdate = convertDate(postdate);
        }

        public static BlogNaverContent of(String title, String link, String description, String bloggername, String bloggerlink, String postdate) {
            return new BlogNaverContent(title, link, description, bloggername, bloggerlink, postdate);
        }

        private LocalDate convertDate(String datetime) {
            int year = Integer.parseInt(datetime.substring(0, 4));
            int month = Integer.parseInt(datetime.substring(4, 6));
            int day = Integer.parseInt(datetime.substring(6, 8));
            return LocalDate.of(year, month, day);
        }
    }
}