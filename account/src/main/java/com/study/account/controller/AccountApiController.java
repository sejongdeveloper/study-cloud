package com.study.account.controller;

import com.study.account.properties.AppProperties;
import com.study.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountApiController {
    private final AppProperties appProperties;
    private final AccountService accountService;

    @GetMapping("/message")
    public String getMessage() {
        log.info("/message");
        return appProperties.getMessage();
    }

    @GetMapping("/key")
    public String getKey() {
        log.info("/key");
        return appProperties.getKey();
    }

    @GetMapping("/todos/message")
    public String getTodoMessage() {
        log.info("/todos/message");
        return accountService.getTodoMessage();
    }

    @GetMapping("/todos/message-fail")
    public String getTodoMessageFail() {
        log.info("/todos/message-fail");
        return accountService.getTodoMessageFail();
    }
}
