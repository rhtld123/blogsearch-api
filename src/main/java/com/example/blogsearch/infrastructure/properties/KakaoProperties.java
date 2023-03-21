package com.example.blogsearch.infrastructure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Getter
@Setter
@ConditionalOnProperty(prefix = "kakao")
public class KakaoProperties {
    private String url;
    private String token;
}
