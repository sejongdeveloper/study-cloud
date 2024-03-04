package com.study.account.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Field {
    private String type;
    private boolean optional;
    private String field;
}
