package com.example.blogsearch.modules.blogsearch.adapter.out.persistence;

import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.repository.BlogSearchRequestRepository;
import com.example.blogsearch.modules.blogsearch.application.port.out.GetBlogPopularSearchKeywordPort;
import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogPopularSearchKeyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BlogSearchRequestPersistenceQueryAdapter implements GetBlogPopularSearchKeywordPort {

    private final BlogSearchRequestRepository blogSearchRequestRepository;

    @Override
    public List<BlogPopularSearchKeyword> getBlogPopularSearchKeywordPort() {
        return blogSearchRequestRepository.findBlogPopularSearchKeywords();
    }
}
