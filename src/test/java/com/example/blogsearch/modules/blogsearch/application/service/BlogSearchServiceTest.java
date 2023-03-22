package com.example.blogsearch.modules.blogsearch.application.service;

import com.example.blogsearch.infrastructure.exception.HttpRequestFailException;
import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.entity.BlogSearchRequestEntity;
import com.example.blogsearch.modules.blogsearch.adapter.out.persistence.entity.Platform;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchCommand;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchPlatform;
import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogSearchResponse;
import com.example.blogsearch.modules.blogsearch.application.port.out.BlogSearchKakaoPort;
import com.example.blogsearch.modules.blogsearch.application.port.out.BlogSearchNaverPort;
import com.example.blogsearch.modules.blogsearch.application.port.out.SaveBlogSearchRequestPort;
import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogSearchKakaoResponse;
import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogSearchNaverResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BlogSearchServiceTest {

    @InjectMocks
    BlogSearchService blogSearchService;
    @Mock
    BlogSearchKakaoPort blogSearchKakaoPort;
    @Mock
    BlogSearchNaverPort blogSearchNaverPort;
    @Mock
    SaveBlogSearchRequestPort saveBlogSearchRequestPort;

    @Test
    void 네이버로_요청_했을_때_illegalArgumentException() {
        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> blogSearchService.search(BlogSearchCommand.of("kakao", "ACCURACY", 1, 10, "NAVER")));
    }

    @Test
    void 카카오로_검색_실패_네이버로도_검색_실패시() {
        //given
        when(blogSearchKakaoPort.search(anyString(), any(), anyInt(), anyInt())).thenThrow(new HttpRequestFailException(HttpStatus.INTERNAL_SERVER_ERROR, "message"));
        when(blogSearchNaverPort.search(anyString(), any(), anyInt(), anyInt())).thenThrow(new HttpRequestFailException(HttpStatus.INTERNAL_SERVER_ERROR, "message"));
        //when
        //then
        assertThrows(HttpRequestFailException.class, () -> blogSearchService.search(BlogSearchCommand.of("카카오", "ACCURACY", 1, 10, "KAKAO")));
        verify(blogSearchKakaoPort, times(1)).search(anyString(), anyString(), anyInt(), anyInt());
        verify(blogSearchNaverPort, times(1)).search(anyString(), anyString(), anyInt(), anyInt());
    }

    @Test
    void 카카오_검색_실패_시_네이버로_검색_성공() {
        //given
        when(blogSearchKakaoPort.search(anyString(), any(), anyInt(), anyInt())).thenThrow(new HttpRequestFailException(HttpStatus.INTERNAL_SERVER_ERROR, "message"));
        when(blogSearchNaverPort.search(anyString(), any(), anyInt(), anyInt())).thenReturn(createNaverResponse());
        when(saveBlogSearchRequestPort.save(any())).thenReturn(BlogSearchRequestEntity.of("카카오", Platform.NAVER));
        //when
        BlogSearchResponse response = blogSearchService.search(BlogSearchCommand.of("카카오", "ACCURACY", 1, 10, "KAKAO"));
        //then
        assertEquals(100L, response.getTotalCount());
        assertEquals(BlogSearchPlatform.NAVER, response.getSearchPlatform());
        assertEquals(10L, response.getNumberOfElements());
        assertEquals(10, response.getContents().size());
        verify(blogSearchKakaoPort, times(1)).search(anyString(), anyString(), anyInt(), anyInt());
        verify(blogSearchNaverPort, times(1)).search(anyString(), anyString(), anyInt(), anyInt());
        verify(saveBlogSearchRequestPort, times(1)).save(any());
    }

    @Test
    void 블로그_검색_성공_카카오() {
        //given
        when(blogSearchKakaoPort.search(anyString(), any(), anyInt(), anyInt())).thenReturn(createKakaoResponse());
        when(saveBlogSearchRequestPort.save(any())).thenReturn(BlogSearchRequestEntity.of("카카오", Platform.KAKAO));
        //when
        BlogSearchResponse response = blogSearchService.search(BlogSearchCommand.of("카카오", "ACCURACY", 1, 10, "KAKAO"));
        //then
        assertEquals(100L, response.getTotalCount());
        assertEquals(BlogSearchPlatform.KAKAO, response.getSearchPlatform());
        assertEquals(10L, response.getNumberOfElements());
        assertEquals(10, response.getContents().size());
        verify(blogSearchKakaoPort, times(1)).search(anyString(), anyString(), anyInt(), anyInt());
        verify(saveBlogSearchRequestPort, times(1)).save(any());
    }

    @Test
    void 카카오_검색_결과가_없을_때_데이터_저장() {
        //given
        BlogSearchKakaoResponse.Meta meta = BlogSearchKakaoResponse.Meta.of(0L, 0L, true);
        when(blogSearchKakaoPort.search(anyString(), any(), anyInt(), anyInt())).thenReturn(BlogSearchKakaoResponse.of(meta, Collections.emptyList()));
        when(saveBlogSearchRequestPort.save(any())).thenReturn(BlogSearchRequestEntity.of("카카오", Platform.KAKAO));
        //when
        BlogSearchResponse response = blogSearchService.search(BlogSearchCommand.of("카카오", "ACCURACY", 1, 10, "KAKAO"));
        //then
        assertEquals(0L, response.getTotalCount());
        assertEquals(BlogSearchPlatform.KAKAO, response.getSearchPlatform());
        assertEquals(0L, response.getNumberOfElements());
        assertTrue(response.getContents().isEmpty());
        verify(blogSearchKakaoPort, times(1)).search(anyString(), anyString(), anyInt(), anyInt());
        verify(saveBlogSearchRequestPort, times(1)).save(any());
    }

    private BlogSearchNaverResponse createNaverResponse() {
        List<BlogSearchNaverResponse.item> items = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> {
            BlogSearchNaverResponse.item item = BlogSearchNaverResponse.item.of("keyword" + i, "link", "description", "bloggername", "bloggerlink", "20230322");
            items.add(item);
        });
        return BlogSearchNaverResponse.of("build", 100L, 1L, 10L, items);
    }

    private BlogSearchKakaoResponse createKakaoResponse() {
        BlogSearchKakaoResponse.Meta meta = BlogSearchKakaoResponse.Meta.of(100L, 10L, false);
        List<BlogSearchKakaoResponse.Documents> documents = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> {
            BlogSearchKakaoResponse.Documents document = BlogSearchKakaoResponse.Documents.of("keyword" + i, "contents", "url", "blog", "thumbnail", "2023-03-04T17:41:00.000+09:00");
            documents.add(document);
        });
        return BlogSearchKakaoResponse.of(meta, documents);
    }
}
