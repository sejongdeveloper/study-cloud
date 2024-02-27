package com.study.account.service;

import com.study.account.apiCaller.TodoApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final TodoApiCaller todoApiCaller;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public String getTodoMessage() {
        CircuitBreaker circuitbreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitbreaker.run(todoApiCaller::getMessage, throwable -> getEmptyMessage());
    }

    public String getTodoMessageFail() {
        CircuitBreaker circuitbreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitbreaker.run(todoApiCaller::getMessageFail, throwable -> getEmptyMessage());
    }

    private static String getEmptyMessage() {
        return "empty message";
    }
}
