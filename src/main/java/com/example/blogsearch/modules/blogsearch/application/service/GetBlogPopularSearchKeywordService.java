package com.example.blogsearch.modules.blogsearch.application.service;

import com.example.blogsearch.modules.blogsearch.application.port.in.GetBlogPopularSearchKeywordUseCase;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogPopluarSearchKeywordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBlogPopularSearchKeywordService implements GetBlogPopularSearchKeywordUseCase {

    @Override
    public BlogPopluarSearchKeywordResponse getBlogPopularSearchKeyword() {
        return null;
    }
}
