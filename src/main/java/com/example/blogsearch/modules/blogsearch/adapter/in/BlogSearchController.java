package com.example.blogsearch.modules.blogsearch.adapter.in;

import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchPlatform;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchSort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search/blog")
public class BlogSearchController {

    @GetMapping("")
    public ResponseEntity<?> searchBlog(@RequestParam(value = "keyword") String keyword,
                                        @RequestParam(value = "sort", required = false) BlogSearchSort sort,
                                        @RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "size", required = false) Integer size,
                                        @RequestParam(value = "platform", required = false) BlogSearchPlatform platform) {
        return ResponseEntity.ok().body(null);
    }
}
