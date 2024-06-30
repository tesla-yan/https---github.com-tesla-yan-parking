package com.smart.parking.exception;

import com.smart.parking.common.enums.ErrorEnum;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class CommonException extends Exception {

    protected ErrorEnum errorEnum;

    public CommonException(@NonNull ErrorEnum errorEnum) {
        super(errorEnum.getDetailMessage());
        this.errorEnum = errorEnum;
    }

    public CommonException(@NonNull ErrorEnum errorEnum, @NonNull String message) {
        super(message);
        this.errorEnum = errorEnum;
    }

    public CommonException(@NonNull ErrorEnum errorEnum, @NonNull Throwable e) {
        super(errorEnum.getDetailMessage(), e);
        this.errorEnum = errorEnum;
    }

    public CommonException(@NonNull ErrorEnum errorEnum, @NonNull String message, @NonNull Throwable e) {
        super(message, e);
        this.errorEnum = errorEnum;
    }
}
