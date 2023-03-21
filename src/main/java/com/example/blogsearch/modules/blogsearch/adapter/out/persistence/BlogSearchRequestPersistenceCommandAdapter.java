package com.example.blogsearch.modules.blogsearch.adapter.out.persistence;

import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.entity.BlogSearchRequestEntity;
import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.repository.BlogSearchRequestRepository;
import com.example.blogsearch.modules.blogsearch.application.port.out.SaveBlogSearchRequestPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class BlogSearchRequestPersistenceCommandAdapter implements SaveBlogSearchRequestPort {

    private final BlogSearchRequestRepository blogSearchRequestRepository;

    @Override
    public void save(BlogSearchRequestEntity entity) {
        blogSearchRequestRepository.save(entity);
    }
}
