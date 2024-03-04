package com.study.account.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageCountHistoryProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, int count) {
        log.info("topic={}, count={}", topic, count);
        Payload payload = getPayload(count);
        KafkaMessageCountHistory kafkaMessageCountHistory = new KafkaMessageCountHistory(payload);
        String data = getData(kafkaMessageCountHistory);
        kafkaTemplate.send(topic, data);
    }

    private static String getData(KafkaMessageCountHistory kafkaMessageCountHistory) {
        String data;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            data = objectMapper.writeValueAsString(kafkaMessageCountHistory);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    private static Payload getPayload(int count) {
        return Payload.builder()
                .count(count)
                .build();
    }
}
