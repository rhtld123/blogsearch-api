package com.example.blogsearch.modules.blogsearch.adapter.out.http;

import com.example.blogsearch.infrastructure.properties.NaverProperties;
import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogSearchNaverResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NaverAdapterTest {

    @InjectMocks
    NaverAdapter naverAdapter;
    @Mock
    NaverProperties naverProperties;
    @Mock
    NaverApiFeignClient naverApiFeignClient;

    @Test
    void 조회_성공() {
        //given
        when(naverProperties.getClientId()).thenReturn("id");
        when(naverProperties.getClientSecret()).thenReturn("secret");
        when(naverApiFeignClient.search(anyString(), anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(ResponseEntity.ok().body(createResponse()));
        //when
        BlogSearchNaverResponse response = naverAdapter.search("keyword", "ACCURACY", 1, 10);
        //then
        verify(naverApiFeignClient, times(1)).search(anyString(), anyString(), anyString(), anyInt(), anyInt(), anyString());
        verify(naverProperties, times(1)).getClientId();
        verify(naverProperties, times(1)).getClientSecret();
        assertEquals(100L, response.getTotal());
        assertEquals(1L, response.getStart());
        assertEquals(10L, response.getDisplay());
        assertTrue(response.getItems().size() == 10);
    }

    @Test
    void naver_properties가_null일_때_NullPointerException() {
        //given
        when(naverProperties.getClientId()).thenReturn(null);
        when(naverProperties.getClientSecret()).thenReturn("secret");
        //when
        //then
        assertThrows(NullPointerException.class, () -> naverAdapter.search("keyword", "ACCURACY", 1, 10));
    }

    private BlogSearchNaverResponse createResponse() {
        List<BlogSearchNaverResponse.item> items = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> {
            BlogSearchNaverResponse.item item = BlogSearchNaverResponse.item.of("keyword" + i, "link", "description", "blog", "link", "");
            items.add(item);
        });
        return BlogSearchNaverResponse.of("build", 100L, 1L, 10L, items);
    }
}