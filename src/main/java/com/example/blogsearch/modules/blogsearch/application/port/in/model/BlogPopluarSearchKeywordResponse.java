package com.example.blogsearch.modules.blogsearch.application.port.in.model;

import lombok.Getter;

@Getter
public class BlogPopluarSearchKeywordResponse {
    private Long count;
    private String keyword;

    private BlogPopluarSearchKeywordResponse(Long count, String keyword) {
        this.count = count;
        this.keyword = keyword;
    }

    public static BlogPopluarSearchKeywordResponse of(Long count, String keyword) {
        return new BlogPopluarSearchKeywordResponse(count, keyword);
    }
}
