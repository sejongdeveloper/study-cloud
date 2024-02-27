package com.study.account.controller;

import com.study.account.properties.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountApiController {
    private final AppProperties appProperties;

    @GetMapping("/hello")
    public String hello() {
        return appProperties.getMessage();
    }

}
