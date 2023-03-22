package com.example.blogsearch.modules.blogsearch.application.port.in.model;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;

import java.util.Objects;

@Getter
public class BlogSearchCommand {
    private String keyword;
    private BlogSearchSort sort;
    private Integer page;
    private Integer size;
    private BlogSearchPlatform platform;

    private BlogSearchCommand(String keyword, BlogSearchSort sort, Integer page, Integer size, BlogSearchPlatform platform) {
        this.keyword = keyword;
        this.sort = Objects.requireNonNullElse(sort, BlogSearchSort.ACCURACY);
        this.page = Objects.requireNonNullElse(page, 1);
        this.size = Objects.requireNonNullElse(size, 10);
        this.platform = Objects.requireNonNullElse(platform, BlogSearchPlatform.KAKAO);
    }

    public static BlogSearchCommand of(String keyword, String sort, Integer page, Integer size, BlogSearchPlatform platform) {
        BlogSearchSort blogSearchSort = null;
        try {
            blogSearchSort = BlogSearchSort.valueOf(sort);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("올바른 정렬 방식이 아닙니다.");
        }
        return new BlogSearchCommand(keyword, blogSearchSort, page, size, platform);
    }
}
