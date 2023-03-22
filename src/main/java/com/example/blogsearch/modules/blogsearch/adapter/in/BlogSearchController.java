package com.example.blogsearch.modules.blogsearch.adapter.in;

import com.example.blogsearch.modules.blogsearch.application.port.in.BlogSearchUseCase;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchCommand;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchPlatform;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchResponse;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchSort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search/blog")
public class BlogSearchController {

    private final BlogSearchUseCase blogSearchUseCase;

    @GetMapping("")
    public ResponseEntity<BlogSearchResponse> searchBlog(@RequestParam(value = "keyword") String keyword,
                                                         @RequestParam(value = "sort", required = false) BlogSearchSort sort,
                                                         @RequestParam(value = "page", required = false) @Min(value = 1, message = "Page는 1 이상이어야 합니다.") @Max(value = 50, message = "Page는 50 이하의 값이여야 합니다.") Integer page,
                                                         @RequestParam(value = "size", required = false) @Min(value = 1, message = "Size는 1 이상이어야 합니다.") @Max(value = 50, message = "Size는 50 이하의 값이어야 합니다.") Integer size,
                                                         @RequestParam(value = "platform", required = false) BlogSearchPlatform platform) {

        return ResponseEntity.ok().body(blogSearchUseCase.search(BlogSearchCommand.of(keyword, sort, page, size, platform)));
    }
}
