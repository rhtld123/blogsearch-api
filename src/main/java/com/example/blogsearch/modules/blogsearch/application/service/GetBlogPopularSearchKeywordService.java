package com.example.blogsearch.modules.blogsearch.application.service;

import com.example.blogsearch.modules.blogsearch.application.port.in.GetBlogPopularSearchKeywordUseCase;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogPopluarSearchKeywordResponse;
import com.example.blogsearch.modules.blogsearch.application.port.out.GetBlogPopularSearchKeywordPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetBlogPopularSearchKeywordService implements GetBlogPopularSearchKeywordUseCase {

    private final GetBlogPopularSearchKeywordPort getBlogPopularSearchKeywordPort;

    @Override
    public BlogPopluarSearchKeywordResponse getBlogPopularSearchKeyword() {
        return BlogPopluarSearchKeywordResponse.from(getSearchKeywords());
    }

    private List<BlogPopluarSearchKeywordResponse.SearchKeyword> getSearchKeywords() {
        return getBlogPopularSearchKeywordPort.getBlogPopularSearchKeywordPort()
                .stream()
                .map(data -> BlogPopluarSearchKeywordResponse.SearchKeyword.of(data.getSearchCount(), data.getKeyword()))
                .collect(Collectors.toList());
    }
}
