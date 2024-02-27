package com.study.account.apiCaller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "todo")
public interface TodoApiCaller {
    @GetMapping("/api/v1/todos/message")
    String getTest();
}
