package com.study.account.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "token")
public class TokenProperties {
    private int expirationTime; //만료시간 (밀리초)
    private String key; //암호
}
