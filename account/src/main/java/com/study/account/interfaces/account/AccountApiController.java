package com.study.account.interfaces.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountApiController {
    @GetMapping("/hello")
    public String hello() {
        return "account project hello!";
    }
}
