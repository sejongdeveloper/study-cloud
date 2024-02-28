package com.study.todo.controller;

import com.study.todo.properties.AppProperties;
import com.study.todo.repository.TodoRepository;
import io.micrometer.core.annotation.Timed;
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
    private final TodoRepository todoRepository;

    @Timed(value = "todo.message", longTask = true)
    @GetMapping("/message")
    public String getMessage() {
        log.info("/message");
        return appProperties.getMessage();
    }

    @Timed(value = "todo.message-count", longTask = true)
    @GetMapping("/message-count")
    public String getMessageCount() {
        log.info("/message-count");
        int messageCount = todoRepository.getCount();
        return String.valueOf(messageCount);
    }
}
