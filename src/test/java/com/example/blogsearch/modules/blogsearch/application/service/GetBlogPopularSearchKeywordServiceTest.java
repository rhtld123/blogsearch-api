package com.example.blogsearch.modules.blogsearch.application.service;

import com.example.blogsearch.modules.blogsearch.application.port.in.model.BlogPopluarSearchKeywordResponse;
import com.example.blogsearch.modules.blogsearch.application.port.out.GetBlogPopularSearchKeywordPort;
import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogPopularSearchKeyword;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetBlogPopularSearchKeywordServiceTest {

    @InjectMocks
    GetBlogPopularSearchKeywordService getBlogPopularSearchKeywordService;
    @Mock
    GetBlogPopularSearchKeywordPort getBlogPopularSearchKeywordPort;

    @Test
    void 조회_성공() {
        //given
        List<BlogPopularSearchKeyword> list = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> {
            list.add(createBlogPopularSearchKeyword(100L - i, "kakao" + i));
        });
        when(getBlogPopularSearchKeywordPort.getBlogPopularSearchKeywordPort()).thenReturn(list);
        //when
        BlogPopluarSearchKeywordResponse response = getBlogPopularSearchKeywordService.getBlogPopularSearchKeyword();
        //then
        verify(getBlogPopularSearchKeywordPort, times(1)).getBlogPopularSearchKeywordPort();
        assertTrue(response.getData().size() == 10);
        assertEquals("kakao0", response.getData().get(0).getKeyword());
        assertEquals(100L, response.getData().get(0).getCount());
    }

    @Test
    void 조회_값_없음() {
        //given
        when(getBlogPopularSearchKeywordPort.getBlogPopularSearchKeywordPort()).thenReturn(Collections.emptyList());
        //when
        BlogPopluarSearchKeywordResponse response = getBlogPopularSearchKeywordService.getBlogPopularSearchKeyword();
        //then
        verify(getBlogPopularSearchKeywordPort, times(1)).getBlogPopularSearchKeywordPort();
        assertTrue(response.getData().isEmpty());
    }

    private BlogPopularSearchKeyword createBlogPopularSearchKeyword(Long count, String keyword) {
        return new BlogPopularSearchKeyword(count, keyword);
    }
}