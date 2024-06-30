package com.smart.parking.common.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public interface ErrorEnum extends BaseEnum {

    String getCode();

    String getMessage();

    default String getDetailMessage() {
        return StringUtils.defaultIfBlank(getMessage(), name());
    }

    String getShowMessage();

    default String getShowMessage(Locale locale) {
        return getShowMessage();
    }
}
