package com.example.blogsearch.modules.blogsearch.application.port.in.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class BlogSearchCommand {
    private String keyword;
    private BlogSearchSort sort;
    private Integer page;
    private Integer size;
    private BlogSearchPlatform platform;

    private BlogSearchCommand(String keyword, String sort, Integer page, Integer size, String platform) {
        this.keyword = keyword;
        this.sort = getBlogSearchSort(sort);
        this.page = Objects.requireNonNullElse(page, 1);
        this.size = Objects.requireNonNullElse(size, 10);
        this.platform = getBlogSearchPlatform(platform);
    }

    public static BlogSearchCommand of(String keyword, String sort, Integer page, Integer size, String platform) {
        return new BlogSearchCommand(keyword, sort, page, size, platform);
    }

    private BlogSearchSort getBlogSearchSort(String sort) {
        try {
            return BlogSearchSort.valueOf(sort);
        } catch (NullPointerException npe) {
            return BlogSearchSort.ACCURACY;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("올바른 정렬 방식이 아닙니다.");
        }
    }

    private BlogSearchPlatform getBlogSearchPlatform(String platform) {
        try {
            return BlogSearchPlatform.valueOf(platform);
        } catch (NullPointerException npe) {
            return BlogSearchPlatform.KAKAO;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("지원하는 플랫폼이 아닙니다.");
        }
    }
}
