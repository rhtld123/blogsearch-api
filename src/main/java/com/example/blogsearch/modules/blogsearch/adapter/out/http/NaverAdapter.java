package com.example.blogsearch.modules.blogsearch.adapter.out.http;

import com.example.blogsearch.infrastructure.properties.NaverProperties;
import com.example.blogsearch.modules.blogsearch.application.port.out.BlogSearchNaverPort;
import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogSearchNaverResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NaverAdapter implements BlogSearchNaverPort {

    private final NaverApiFeignClient naverApiFeignClient;
    private final NaverProperties naverProperties;

    @Override
    public BlogSearchNaverResponse search(String query, String sort, int start, int display) {
        return naverApiFeignClient.search(naverProperties.getClientId(), naverProperties.getClientSecret(), query, display, start, sort).getBody();
    }

}
