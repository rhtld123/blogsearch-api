package com.example.blogsearch.modules.blogsearch.adapter.in;

import com.example.blogsearch.modules.blogsearch.application.port.in.GetBlogPopularSearchKeywordUseCase;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogPopluarSearchKeywordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search/blog")
public class GetBlogPopularSearchKeywordController {

    private final GetBlogPopularSearchKeywordUseCase getBlogPopularSearchKeywordUseCase;

    @GetMapping("/popular-search-keyword")
    public ResponseEntity<BlogPopluarSearchKeywordResponse> popularSearches() {
        return ResponseEntity.ok().body(getBlogPopularSearchKeywordUseCase.getBlogPopularSearchKeyword());
    }
}
