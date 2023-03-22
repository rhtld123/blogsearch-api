package com.example.blogsearch.modules.blogsearch.adapter.out.persistence;

import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.entity.BlogSearchRequestEntity;
import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.entity.Platform;
import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.repository.BlogSearchRequestRepository;
import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogPopularSearchKeyword;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BlogSearchRequestPersistenceQueryAdapterTest {

    @Autowired
    BlogSearchRequestPersistenceQueryAdapter blogSearchRequestPersistenceQueryAdapter;

    @Autowired
    BlogSearchRequestRepository repository;

    @PersistenceContext
    EntityManager entityManager;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void 정상_조회_카카오_1등() {
        //given
        IntStream.range(0, 20).forEach(i -> createEntity("카카오", Platform.KAKAO));
        IntStream.range(0, 10).forEach(i -> createEntity("네이버", Platform.KAKAO));
        IntStream.range(0, 10).forEach(i -> createEntity("keyword" + i, Platform.KAKAO));
        entityManager.clear();
        //when
        List<BlogPopularSearchKeyword> keywords = blogSearchRequestPersistenceQueryAdapter.getBlogPopularSearchKeywordPort();
        //then
        assertFalse(keywords.isEmpty());
        assertEquals("카카오", keywords.get(0).getKeyword());
        assertEquals(20, keywords.get(0).getSearchCount());
        assertEquals("네이버", keywords.get(1).getKeyword());
        assertEquals(10, keywords.get(1).getSearchCount());
    }

    @Test
    void 검색_요청_내역이_없을_때() {
        //given
        //when
        List<BlogPopularSearchKeyword> keywords = blogSearchRequestPersistenceQueryAdapter.getBlogPopularSearchKeywordPort();
        //then
        assertTrue(keywords.isEmpty());
        assertNotNull(keywords);
    }

    private void createEntity(String keyword, Platform platform) {
        repository.save(BlogSearchRequestEntity.of(keyword, platform));
    }
}