package com.example.blogsearch.infrastructure.config.feign;

import com.example.blogsearch.infrastructure.exception.HttpRequestFailException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String requestUrl = response.request().url();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());
        log.error("{} 호출 실패 : status = {}", requestUrl, responseStatus);
        return new HttpRequestFailException("처리중 오류가 발생하였습니다.");
    }
}