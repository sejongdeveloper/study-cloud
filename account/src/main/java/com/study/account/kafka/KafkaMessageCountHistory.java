package com.study.account.kafka;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class KafkaMessageCountHistory implements Serializable {
    private Schema schema;
    private Payload payload;

    public KafkaMessageCountHistory(Payload payload) {
        List<Field> fields = List.of(
                Field.builder().type("int32").optional(false).field("count").build()
        );
        this.schema = Schema.builder()
                .type("struct")
                .fields(fields)
                .optional(false)
                .name("message_count_history")
                .build();
        this.payload = payload;
    }
}
