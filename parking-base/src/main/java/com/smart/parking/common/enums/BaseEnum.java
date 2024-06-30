package com.smart.parking.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public interface BaseEnum<Value> {

    String METHOD_NAME = "getValue";

    @JsonValue
    Value getValue();

    String name();
}
