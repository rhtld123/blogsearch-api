package com.example.blogsearch.modules.blogsearch.application.port.in.model;

import lombok.Getter;

import java.util.List;

@Getter
public class BlogPopluarSearchKeywordResponse {

    private List<SearchKeyword> data;

    private BlogPopluarSearchKeywordResponse(List<SearchKeyword> data) {
        this.data = data;
    }

    public static BlogPopluarSearchKeywordResponse from(List<SearchKeyword> data) {
        return new BlogPopluarSearchKeywordResponse(data);
    }

    @Getter
    public static class SearchKeyword {
        private Long count;
        private String keyword;

        private SearchKeyword(Long count, String keyword) {
            this.count = count;
            this.keyword = keyword;
        }

        public static SearchKeyword of(Long count, String keyword) {
            return new SearchKeyword(count, keyword);
        }
    }
}
