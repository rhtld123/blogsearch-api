package com.example.blogsearch.modules.blogsearch.adapter.out.http;

import com.example.blogsearch.infrastructure.properties.KakaoProperties;
import com.example.blogsearch.modules.blogsearch.application.port.out.BlogSearchKakaoPort;
import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogSearchKakaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoAdapter implements BlogSearchKakaoPort {

    private final KakaoApiFeignClient kakaoApiFeignClient;
    private final KakaoProperties kakaoProperties;

    @Override
    public BlogSearchKakaoResponse search(String query, String sort, int page, int size) {
        String accessToken = String.format("KakaoAK %s", kakaoProperties.getToken());
        return kakaoApiFeignClient.searchBlog(accessToken, query, sort, page, size).getBody();
    }
}
