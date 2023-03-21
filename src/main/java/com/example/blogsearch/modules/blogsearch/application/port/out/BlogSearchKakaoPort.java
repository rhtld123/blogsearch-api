package com.example.blogsearch.modules.blogsearch.application.port.out;

import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogSearchKakaoResponse;

public interface BlogSearchKakaoPort {
    BlogSearchKakaoResponse search(String query, String sort, int page, int size);
}
