package com.example.blogsearch.modules.blogsearch.adapter.out.http;

import com.example.blogsearch.infrastructure.properties.KakaoProperties;
import com.example.blogsearch.modules.blogsearch.application.port.out.model.BlogSearchKakaoResponse;
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
class KakaoAdapterTest {

    @InjectMocks
    KakaoAdapter kakaoAdapter;
    @Mock
    KakaoApiFeignClient kakaoApiFeignClient;
    @Mock
    KakaoProperties kakaoProperties;

    @Test
    void 조회_성공() {
        //given
        when(kakaoProperties.getToken()).thenReturn("token");
        when(kakaoApiFeignClient.searchBlog(anyString(), anyString(), anyString(), anyInt(), anyInt())).thenReturn(ResponseEntity.ok().body(createResponse()));
        //when
        BlogSearchKakaoResponse response = kakaoAdapter.search("keyword", "ACCURACY", 1, 10);
        //then
        verify(kakaoApiFeignClient, times(1)).searchBlog(anyString(), anyString(), anyString(), anyInt(), anyInt());
        verify(kakaoProperties,times(1)).getToken();
        BlogSearchKakaoResponse.Meta meta = response.getMeta();
        assertEquals(100L, meta.getTotalCount());
        assertEquals(10L, meta.getPageableCount());
        assertFalse(meta.isEnd());
        assertTrue(response.getDocuments().size() == 10);
    }

    @Test
    void kakao_properties가_null일_때_NullPointerException() {
        //given
        when(kakaoProperties.getToken()).thenReturn(null);
        //when
        //then
        assertThrows(NullPointerException.class, () -> kakaoAdapter.search("keyword", "ACCURACY", 1, 10));
    }

    private BlogSearchKakaoResponse createResponse() {
        BlogSearchKakaoResponse.Meta meta = BlogSearchKakaoResponse.Meta.of(100L, 10L, false);
        List<BlogSearchKakaoResponse.Documents> documents = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> {
            BlogSearchKakaoResponse.Documents document = BlogSearchKakaoResponse.Documents.of("keyword" + i, "contents", "url", "blog", "thumbnail", "");
            documents.add(document);
        });
        return BlogSearchKakaoResponse.of(meta, documents);
    }
}