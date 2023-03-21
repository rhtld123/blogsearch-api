package com.example.blogsearch.modules.blogsearch.adapter.out.http;

import com.example.blogsearch.infrastructure.config.feign.FeignConfiguration;
import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogSearchNaverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naverBlogSearchClient", url = "${naver.url}", configuration = FeignConfiguration.class)
public interface NaverApiFeignClient {

    @GetMapping("/v1/search/blog.json")
    public ResponseEntity<BlogSearchNaverResponse> search(@RequestHeader("X-Naver-Client-Id") String clientId,
                                                          @RequestHeader("X-Naver-Client-Secret") String clientSecret,
                                                          @RequestParam("query") String query,
                                                          @RequestParam("display") int display,
                                                          @RequestParam("start") int start,
                                                          @RequestParam("sort") String sort);
}
