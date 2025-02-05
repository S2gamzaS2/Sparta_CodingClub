package com.sparta.myselectshop.naver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix="naver.client")
public class NaverApiConfig {
    private String id;
    private String pw;

}
