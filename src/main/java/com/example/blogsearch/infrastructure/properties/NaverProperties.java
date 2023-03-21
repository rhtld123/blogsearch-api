package com.example.blogsearch.infrastructure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "naver")
public class NaverProperties {
    private String url;
    private String clientId;
    private String clientSecret;
}
