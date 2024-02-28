package com.study.todo.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    private final TodoRepository todoRepository;

    @KafkaListener(topics = "message-count", groupId = "consumerGroupId")
    public void updateMessageCount(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Integer messageCount = objectMapper.readValue(data, new TypeReference<>() {});
        todoRepository.plusCount(messageCount);
    }
}
