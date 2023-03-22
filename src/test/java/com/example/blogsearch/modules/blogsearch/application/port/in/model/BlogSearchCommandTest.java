package com.example.blogsearch.modules.blogsearch.application.port.in.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BlogSearchCommandTest {

    @Test
    void sort_null이면_ACCURACY_반환() {
        //given
        //when
        BlogSearchCommand command = BlogSearchCommand.of("test", null, 0, 10, "KAKAO");
        //then
        assertEquals(BlogSearchSort.ACCURACY, command.getSort());
    }

    @Test
    void sort_ACCURACY() {
        //given
        //when
        BlogSearchCommand command = BlogSearchCommand.of("test", "ACCURACY", 0, 10, "KAKAO");
        //then
        assertEquals(BlogSearchSort.ACCURACY, command.getSort());
    }

    @Test
    void sort_RECENCY() {
        //given
        //when
        BlogSearchCommand command = BlogSearchCommand.of("test", "RECENCY", 0, 10, "KAKAO");
        //then
        assertEquals(BlogSearchSort.RECENCY, command.getSort());
    }

    @Test
    void sort_이상하게_입력했을_때_NPE() {
        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> BlogSearchCommand.of("test", "KAKAO", 0, 10, "KAKAO"));
    }

    @Test
    void 플랫폼_카카오() {
        //given
        //when
        BlogSearchCommand command = BlogSearchCommand.of("test", null, 0, 10, "KAKAO");
        //then
        assertEquals(BlogSearchPlatform.KAKAO, command.getPlatform());
    }

    @Test
    void 플랫폼_네이버() {
        //given
        //when
        BlogSearchCommand command = BlogSearchCommand.of("test", null, 0, 10, "NAVER");
        //then
        assertEquals(BlogSearchPlatform.NAVER, command.getPlatform());
    }

    @Test
    void 플랫폼_이상하게_입력했을_때_npe() {
        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> BlogSearchCommand.of("test", null, 0, 10, "EMPAS"));
    }
}
