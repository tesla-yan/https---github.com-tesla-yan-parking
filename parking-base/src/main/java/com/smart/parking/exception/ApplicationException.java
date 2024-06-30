package com.smart.parking.exception;

import com.smart.parking.common.enums.ErrorEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper=false)
public class ApplicationException extends RuntimeException {

    /**
     * errorEnum
     */
    private ErrorEnum errorEnum;

    /**
     * errorMsg
     */
    private String errorMsg;

    public ApplicationException(@NotNull ErrorEnum errorEnum){
        super(errorEnum.getShowMessage());
        this.setErrorEnum(errorEnum);
    }

    public ApplicationException(@NotNull ErrorEnum errorEnum, @NotNull String errorMsg){
        super(errorMsg);
        this.setErrorEnum(errorEnum);
        this.setErrorMsg(errorMsg);
    }
}
