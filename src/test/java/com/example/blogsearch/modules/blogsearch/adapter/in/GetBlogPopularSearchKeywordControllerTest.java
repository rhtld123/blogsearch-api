package com.example.blogsearch.modules.blogsearch.adapter.in;

import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.entity.BlogSearchRequestEntity;
import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.entity.Platform;
import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.repository.BlogSearchRequestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GetBlogPopularSearchKeywordControllerTest {

    @Autowired
    MockMvc mockMvc;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    BlogSearchRequestRepository repository;

    @Test
    void 인기검색어_조회_결과_있음() throws Exception {
        //given
        IntStream.range(0, 20).forEach(i -> createEntity("카카오", Platform.KAKAO));
        IntStream.range(0, 10).forEach(i -> createEntity("네이버", Platform.KAKAO));
        IntStream.range(0, 10).forEach(i -> createEntity("keyword" + i, Platform.KAKAO));
        entityManager.clear();
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/blog/popular-search-keyword")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].count").value("20"))
                .andExpect(jsonPath("$.data[0].keyword").value("카카오"));
    }

    @Test
    void 인기검색어_조회_결과_없음() throws Exception {
        //given
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/blog/popular-search-keyword")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    private void createEntity(String keyword, Platform platform) {
        repository.save(BlogSearchRequestEntity.of(keyword, platform));
    }
}