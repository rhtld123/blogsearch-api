package com.example.blogsearch.modules.blogsearch.adapter.out.http;

import com.example.blogsearch.modules.blogsearch.application.port.out.BlogSearchKakaoPort;
import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogSearchKakaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoAdapter implements BlogSearchKakaoPort {

    @Override
    public BlogSearchKakaoResponse search(String query, String sort, int page, int size) {
        return null;
    }
}
