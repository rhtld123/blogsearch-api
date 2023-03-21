package com.example.blogsearch.modules.blogsearch.adapter.out.persistence;

import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.entity.BlogSearchRequestEntity;
import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.entity.Platform;
import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.repository.BlogSearchRequestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class BlogSearchRequestPersistenceCommandAdapterTest {

    @Autowired
    BlogSearchRequestPersistenceCommandAdapter blogSearchRequestPersistenceCommandAdapter;

    @Autowired
    BlogSearchRequestRepository repository;

    @PersistenceContext
    EntityManager entityManager;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void 등록_성공() {
        //given
        BlogSearchRequestEntity entity = BlogSearchRequestEntity.of("test", Platform.KAKAO);
        //when
        Long id = blogSearchRequestPersistenceCommandAdapter.save(entity).getId();
        entityManager.clear();
        //then
        assertTrue(repository.findById(id).isPresent());
    }
}