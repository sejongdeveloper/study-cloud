package com.study.account.service;

import com.study.account.apiCaller.TodoApiCaller;
import com.study.account.kafka.KafkaProducer;
import com.study.account.kafka.MessageCountHistoryProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    private final TodoApiCaller todoApiCaller;
    private final CircuitBreakerFactory circuitBreakerFactory;
    private final KafkaProducer kafkaProducer;
    private final PasswordEncoder passwordEncoder;
    private final MessageCountHistoryProducer messageCountHistoryProducer;

    public String getTodoMessage() {
        kafkaProducer.send("message-count", 1);
        messageCountHistoryProducer.send("message_count_history", 1);
        CircuitBreaker circuitbreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitbreaker.run(todoApiCaller::getMessage, throwable -> getEmptyMessage());
    }

    public String getTodoMessageFail() {
        kafkaProducer.send("message-count", -1);
        messageCountHistoryProducer.send("message_count_history", -1);
        CircuitBreaker circuitbreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitbreaker.run(todoApiCaller::getMessageFail, throwable -> getEmptyMessage());
    }

    private static String getEmptyMessage() {
        return "empty message";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = passwordEncoder.encode("1234");
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
