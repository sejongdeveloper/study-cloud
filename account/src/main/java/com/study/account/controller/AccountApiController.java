package com.study.account.controller;

import com.study.account.properties.AppProperties;
import com.study.account.service.AccountService;
import io.micrometer.core.annotation.Timed;
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

    @Timed(value = "account.message", longTask = true)
    @GetMapping("/message")
    public String getMessage() {
        log.info("/message");
        return appProperties.getMessage();
    }

    @Timed(value = "account.key", longTask = true)
    @GetMapping("/key")
    public String getKey() {
        log.info("/key");
        return appProperties.getKey();
    }

    @Timed(value = "account.todo.message", longTask = true)
    @GetMapping("/todos/message")
    public String getTodoMessage() {
        log.info("/todos/message");
        return accountService.getTodoMessage();
    }

    @Timed(value = "account.todo.message-fail", longTask = true)
    @GetMapping("/todos/message-fail")
    public String getTodoMessageFail() {
        log.info("/todos/message-fail");
        return accountService.getTodoMessageFail();
    }
}
