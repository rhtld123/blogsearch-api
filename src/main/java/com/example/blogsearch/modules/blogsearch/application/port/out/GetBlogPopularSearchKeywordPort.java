package com.example.blogsearch.modules.blogsearch.application.port.out;

import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogPopularSearchKeyword;

import java.util.List;

public interface GetBlogPopularSearchKeywordPort {
    List<BlogPopularSearchKeyword> getBlogPopularSearchKeywordPort();
}
