package com.example.blogsearch.modules.blogsearch.application.port.out.model;

import lombok.Getter;

@Getter
public class BlogPopularSearchKeyword {

    private Long searchCount;
    private String keyword;

    public BlogPopularSearchKeyword(Long searchCount, String keyword) {
        this.searchCount = searchCount;
        this.keyword = keyword;
    }
}
