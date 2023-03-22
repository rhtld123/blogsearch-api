package com.example.blogsearch.modules.blogsearch.adapter.in;

import com.example.blogsearch.infrastructure.exception.HttpRequestFailException;
import com.example.blogsearch.modules.blogsearch.adapter.out.http.KakaoApiFeignClient;
import com.example.blogsearch.modules.blogsearch.adapter.out.http.NaverApiFeignClient;
import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogSearchKakaoResponse;
import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogSearchNaverResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogSearchControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    KakaoApiFeignClient kakaoApiFeignClient;

    @MockBean
    NaverApiFeignClient naverApiFeignClient;

    @Test
    void sort_잘못_입력했을_때_오류_발생() throws Exception {
        //given
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("keyword", "카카오")
                        .param("sort", "sort")
                        .param("page", "1")
                        .param("size", "10")
                        .param("platform", "KAKAO"))
                .andDo(print())
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("올바른 정렬 방식이 아닙니다."));
    }

    @Test
    void page가_1보다_작은_값_일_때_오류_발생() throws Exception {
        //given
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("keyword", "카카오")
                        .param("sort", "ACCURACY")
                        .param("page", "0")
                        .param("size", "10")
                        .param("platform", "KAKAO"))
                .andDo(print())
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Page는 1 이상이어야 합니다."));
    }

    @Test
    void page가_50_이상_일_때_오류_발생() throws Exception {
        //given
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("keyword", "카카오")
                        .param("sort", "ACCURACY")
                        .param("page", "51")
                        .param("size", "10")
                        .param("platform", "KAKAO"))
                .andDo(print())
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Page는 50 이하의 값이여야 합니다."));
    }

    @Test
    void size가_1보다_작은_값_일_때_오류_발생() throws Exception {
        //given
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("keyword", "카카오")
                        .param("sort", "ACCURACY")
                        .param("page", "1")
                        .param("size", "0")
                        .param("platform", "KAKAO"))
                .andDo(print())
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Size는 1 이상이어야 합니다."));

    }

    @Test
    void size가_50_이상_일_때_오류_발생() throws Exception {
        //given
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("keyword", "카카오")
                        .param("sort", "ACCURACY")
                        .param("page", "1")
                        .param("size", "51")
                        .param("platform", "KAKAO"))
                .andDo(print())
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Size는 50 이하의 값이어야 합니다."));
    }

    @Test
    void 네이버로_요청시_지원하지_않는_플랫폼() throws Exception {
        //given
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("keyword", "카카오")
                        .param("sort", "ACCURACY")
                        .param("page", "1")
                        .param("size", "10")
                        .param("platform", "NAVER"))
                .andDo(print())
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("지원하지 않는 검색 플랫폼 입니다."));
    }

    @Test
    void sort_이상한_값_입력_시_400() throws Exception {
        //given
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("keyword", "카카오")
                        .param("sort", "잘못정렬")
                        .param("page", "1")
                        .param("size", "10")
                        .param("platform", "NAVER"))
                .andDo(print())
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("올바른 정렬 방식이 아닙니다."));
    }

    @Test
    void platform_이상한_값_입력_시_400() throws Exception {
        //given
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("keyword", "카카오")
                        .param("page", "1")
                        .param("size", "10")
                        .param("platform", "NETMARBLE"))
                .andDo(print())
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("지원하는 플랫폼이 아닙니다."));
    }

    @Test
    void 카카오_실패_네이버_실패() throws Exception {
        //given
        when(kakaoApiFeignClient.searchBlog(anyString(), anyString(), anyString(), anyInt(), anyInt())).thenThrow(new HttpRequestFailException(HttpStatus.INTERNAL_SERVER_ERROR, "message"));
        when(naverApiFeignClient.search(anyString(), anyString(), anyString(), anyInt(), anyInt(), anyString())).thenThrow(new HttpRequestFailException(HttpStatus.INTERNAL_SERVER_ERROR, "message"));
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("keyword", "카카오")
                        .param("sort", "ACCURACY")
                        .param("page", "1")
                        .param("size", "10")
                        .param("platform", "KAKAO"))
                .andDo(print())
                //then
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("message"));
    }

    @Test
    void 카카오_실패_네이버_검색_성공() throws Exception {
        //given
        List<BlogSearchNaverResponse.item> items = new ArrayList<>();
        IntStream.range(0, 2).forEach(i -> {
            BlogSearchNaverResponse.item item = BlogSearchNaverResponse.item.of("keyword" + i, "link", "description", "blog", "link", "20230101");
            items.add(item);
        });
        BlogSearchNaverResponse response = BlogSearchNaverResponse.of("build", 2L, 1L, 2L, items);
        when(kakaoApiFeignClient.searchBlog(anyString(), anyString(), anyString(), anyInt(), anyInt())).thenThrow(new HttpRequestFailException(HttpStatus.INTERNAL_SERVER_ERROR, "message"));
        when(naverApiFeignClient.search(anyString(), anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(ResponseEntity.ok(response));
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("keyword", "카카오")
                        .param("sort", "ACCURACY")
                        .param("page", "1")
                        .param("size", "10")
                        .param("platform", "KAKAO"))
                .andDo(print())
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCount").value("2"))
                .andExpect(jsonPath("$.numberOfElements").value("2"))
                .andExpect(jsonPath("$.numberOfElements").value("2"))
                .andExpect(jsonPath("$.contents[0].title").value("keyword0"))
                .andExpect(jsonPath("$.contents[0].link").value("link"))
                .andExpect(jsonPath("$.contents[0].description").value("description"))
                .andExpect(jsonPath("$.contents[0].bloggername").value("blog"))
                .andExpect(jsonPath("$.contents[0].bloggerlink").value("link"))
                .andExpect(jsonPath("$.contents[0].postdate").value("2023-01-01"))
                .andExpect(jsonPath("$.contents[1].title").value("keyword1"))
                .andExpect(jsonPath("$.contents[1].link").value("link"))
                .andExpect(jsonPath("$.contents[1].description").value("description"))
                .andExpect(jsonPath("$.contents[1].bloggername").value("blog"))
                .andExpect(jsonPath("$.contents[1].bloggerlink").value("link"))
                .andExpect(jsonPath("$.contents[1].postdate").value("2023-01-01"))
                .andExpect(jsonPath("$.searchPlatform").value("NAVER"));
    }

    @Test
    void 카카오_실패_네이버_검색결과_없음() throws Exception {
        when(kakaoApiFeignClient.searchBlog(anyString(), anyString(), anyString(), anyInt(), anyInt())).thenThrow(new HttpRequestFailException(HttpStatus.INTERNAL_SERVER_ERROR, "message"));
        when(naverApiFeignClient.search(anyString(), anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(ResponseEntity.ok(BlogSearchNaverResponse.of("build", 0L, 0L, 0L, Collections.emptyList())));
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("keyword", "카카오")
                        .param("sort", "ACCURACY")
                        .param("page", "1")
                        .param("size", "10")
                        .param("platform", "KAKAO"))
                .andDo(print())
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCount").value("0"))
                .andExpect(jsonPath("$.numberOfElements").value("0"))
                .andExpect(jsonPath("$.contents").isEmpty())
                .andExpect(jsonPath("$.searchPlatform").value("NAVER"));
    }

    @Test
    void 카카오_검색_결과_없음() throws Exception {
        //given
        BlogSearchKakaoResponse.Meta meta = BlogSearchKakaoResponse.Meta.of(0L, 0L, true);
        when(kakaoApiFeignClient.searchBlog(anyString(), anyString(), anyString(), anyInt(), anyInt())).thenReturn(ResponseEntity.ok(BlogSearchKakaoResponse.of(meta, Collections.emptyList())));
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("keyword", "카카오")
                        .param("sort", "ACCURACY")
                        .param("page", "1")
                        .param("size", "10")
                        .param("platform", "KAKAO"))
                .andDo(print())
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCount").value("0"))
                .andExpect(jsonPath("$.numberOfElements").value("0"))
                .andExpect(jsonPath("$.contents").isEmpty())
                .andExpect(jsonPath("$.searchPlatform").value("KAKAO"));
    }

    @Test
    void 카카오_조회_성공() throws Exception {
        //given
        BlogSearchKakaoResponse.Meta meta = BlogSearchKakaoResponse.Meta.of(2L, 1L, true);
        List<BlogSearchKakaoResponse.Documents> documents = new ArrayList<>();
        IntStream.range(0, 2).forEach(i -> {
            BlogSearchKakaoResponse.Documents document = BlogSearchKakaoResponse.Documents.of("keyword" + i, "contents", "url", "blog", "thumbnail", "2023-03-13T17:09:00.000+09:00");
            documents.add(document);
        });
        BlogSearchKakaoResponse response = BlogSearchKakaoResponse.of(meta, documents);
        when(kakaoApiFeignClient.searchBlog(anyString(), anyString(), anyString(), anyInt(), anyInt())).thenReturn(ResponseEntity.ok(response));
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("keyword", "카카오")
                        .param("sort", "ACCURACY")
                        .param("page", "1")
                        .param("size", "10")
                        .param("platform", "KAKAO"))
                .andDo(print())
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCount").value("2"))
                .andExpect(jsonPath("$.numberOfElements").value("2"))
                .andExpect(jsonPath("$.numberOfElements").value("2"))
                .andExpect(jsonPath("$.contents[0].title").value("keyword0"))
                .andExpect(jsonPath("$.contents[0].contents").value("contents"))
                .andExpect(jsonPath("$.contents[0].url").value("url"))
                .andExpect(jsonPath("$.contents[0].blogName").value("blog"))
                .andExpect(jsonPath("$.contents[0].thumbnail").value("thumbnail"))
                .andExpect(jsonPath("$.contents[0].dateTime").value("2023-03-13T17:09:00"))
                .andExpect(jsonPath("$.contents[1].title").value("keyword1"))
                .andExpect(jsonPath("$.contents[1].contents").value("contents"))
                .andExpect(jsonPath("$.contents[1].url").value("url"))
                .andExpect(jsonPath("$.contents[1].blogName").value("blog"))
                .andExpect(jsonPath("$.contents[1].thumbnail").value("thumbnail"))
                .andExpect(jsonPath("$.contents[1].dateTime").value("2023-03-13T17:09:00"))
                .andExpect(jsonPath("$.searchPlatform").value("KAKAO"));
    }
}