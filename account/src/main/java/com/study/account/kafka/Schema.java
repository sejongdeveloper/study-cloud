package com.study.account.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Schema {
    private String type;
    private List<Field> fields;
    private boolean optional;
    private String name;
}
