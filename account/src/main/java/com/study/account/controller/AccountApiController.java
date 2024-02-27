package com.study.account.controller;

import com.study.account.properties.AppProperties;
import com.study.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountApiController {
    private final AppProperties appProperties;
    private final AccountService accountService;

    @GetMapping("/message")
    public String getMessage() {
        return appProperties.getMessage();
    }

    @GetMapping("/key")
    public String getKey() {
        return appProperties.getKey();
    }

    @GetMapping("/todos/message")
    public String getTodoMessage() {
        return accountService.getTodoMessage();
    }

    @GetMapping("/todos/message-fail")
    public String getTodoMessageFail() {
        return accountService.getTodoMessageFail();
    }
}
