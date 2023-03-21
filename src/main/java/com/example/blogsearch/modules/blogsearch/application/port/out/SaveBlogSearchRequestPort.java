package com.example.blogsearch.modules.blogsearch.application.port.out;

import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.entity.BlogSearchRequestEntity;

public interface SaveBlogSearchRequestPort {
    BlogSearchRequestEntity save(BlogSearchRequestEntity entity);
}
