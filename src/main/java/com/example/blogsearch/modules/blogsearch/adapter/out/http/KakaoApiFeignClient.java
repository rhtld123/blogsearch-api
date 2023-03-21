package com.example.blogsearch.modules.blogsearch.adapter.out.http;

import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogSearchKakaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakaoBlogSearchClient", url = "${kakao.url}")
public interface KakaoApiFeignClient {

    @GetMapping("/v2/search/blog")
    ResponseEntity<BlogSearchKakaoResponse> searchBlog(@RequestHeader("Authorization") String accessToken,
                                                       @RequestParam(value = "query") String query,
                                                       @RequestParam(value = "sort", required = false) String sort,
                                                       @RequestParam(value = "page", required = false) int page,
                                                       @RequestParam(value = "size", required = false) int size);
}
