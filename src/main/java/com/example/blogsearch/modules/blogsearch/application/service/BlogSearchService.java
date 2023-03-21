package com.example.blogsearch.modules.blogsearch.application.service;

import com.example.blogsearch.modules.blogsearch.application.port.in.BlogSearchUseCase;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchCommand;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogSearchService implements BlogSearchUseCase {

    @Override
    public BlogSearchResponse search(BlogSearchCommand blogSearchCommand) {
        return null;
    }
}
