package com.smart.parking.common;

import com.smart.parking.common.enums.CommonErrorEnum;
import com.smart.parking.common.enums.ErrorEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Data
public class ApiResult<DATA> implements Serializable {

    @NonNull
    Integer respCode;
    @NonNull
    String respMsg;
    String errorCode;
    DATA data;

    public static boolean isSuccessful(ApiResult<?> result) {
        return Objects.nonNull(result) && Objects.equals(RespCode.SUCCESS, result.getRespCode());
    }

    public static boolean isFailed(ApiResult<?> result) {
        return !isSuccessful(result);
    }

    public static <T> ApiResult<T> success() {
        return new ApiResult<>(RespCode.SUCCESS, CommonErrorEnum.SUCCESS.getDetailMessage(), CommonErrorEnum.SUCCESS.getCode(), null);
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = success();
        result.setData(data);
        return result;
    }

    public static <T> ApiResult<T> error(ErrorEnum errorEnum) {
        return error(errorEnum, null);
    }

    public static <T> ApiResult<T> error(ErrorEnum errorEnum, String errMsg) {
        return error(errorEnum, errMsg, null);
    }

    public static <T> ApiResult<T> error(ErrorEnum errorEnum, T data) {
        return error(errorEnum, null, data);
    }

    public static <T> ApiResult<T> error(ErrorEnum errorEnum, String errMsg, T data) {
        return new ApiResult<>(RespCode.FAILED, StringUtils.defaultIfBlank(errMsg, errorEnum.getShowMessage()), errorEnum.getCode(), data);
    }

    public Integer getRespCode() {
        return respCode;
    }

    public void setRespCode(Integer respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public DATA getData() {
        return data;
    }

    public void setData(DATA data) {
        this.data = data;
    }

    public ApiResult(@NonNull Integer respCode, @NonNull String respMsg, String errorCode, DATA data) {
        this.respCode = respCode;
        this.respMsg = respMsg;
        this.errorCode = errorCode;
        this.data = data;
    }

    public interface RespCode{
        Integer SUCCESS = 0;
        Integer FAILED = -1;
    }
}