package com.study.todo.controller;

import com.study.todo.properties.AppProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoApiController {
    private final AppProperties appProperties;

    @GetMapping("/message")
    public String getMessage() {
        log.info("/message");
        return appProperties.getMessage();
    }
}
