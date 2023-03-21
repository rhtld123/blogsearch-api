package com.example.blogsearch.modules.blogsearch.application.port.out;

import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogSearchNaverResponse;

public interface BlogSearchNaverPort {
    BlogSearchNaverResponse search(String query, String sort, int start, int display);
}
