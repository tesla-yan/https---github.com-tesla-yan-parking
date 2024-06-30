package com.smart.parking.exception.handler;

import com.smart.parking.common.ApiResult;
import com.smart.parking.common.enums.CommonErrorEnum;
import com.smart.parking.common.enums.ErrorEnum;
import com.smart.parking.exception.ApplicationException;
import com.smart.parking.exception.BusinessException;
import com.smart.parking.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static com.smart.parking.common.enums.CommonErrorEnum.PARAM_INVALID;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static ApiResult<?> handle(Exception e, ErrorEnum errorEnum) {
        log.error(e.getMessage(), e);
        return handle(e, errorEnum, null);
    }

    private static ApiResult<?> handle(Exception e, ErrorEnum errorEnum, String errMsg) {
        log.error(e.getMessage(), e);
        return ApiResult.error(errorEnum, errMsg);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<?> bindException(BindException e) {
        String errMsg = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> String.format("%s:%s", fieldError.getField(), fieldError.getDefaultMessage()))
                .findFirst().orElse(PARAM_INVALID.getShowMessage());
        List list = e.getBindingResult().getFieldErrors();
        for (Object object : list) {
            if (object instanceof FieldError) {
                String[] codes = ((FieldError) object).getCodes();
                for (String code : codes) {
                    if (StringUtils.endsWithIgnoreCase(code, "typeMismatch")) {
                        errMsg = "请输入正确的参数格式";
                    }
                }
            }
        }
        return handle(e, PARAM_INVALID, errMsg);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<?> constraintViolationException(ConstraintViolationException e) {
        return handle(e, PARAM_INVALID, e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<?> missingServletRequestParameterException(Exception e) {
        return handle(e, CommonErrorEnum.PARAM_ABSENT, e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ApiResult<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return handle(e, CommonErrorEnum.HTTP_REQUEST_METHOD_INVALID);
    }

    @ExceptionHandler(CommonException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<?> commonException(CommonException e) {
        return handle(e, e.getErrorEnum());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<?> internalServerError(Exception e) {
        return handle(e, CommonErrorEnum.UNKNOWN_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<?> internalServerError(BusinessException e) {
        return handle(e, CommonErrorEnum.UNKNOWN_ERROR);
    }

    @ExceptionHandler(ApplicationException.class)
    public ApiResult<?> applicationException(ApplicationException applicationException) {
        return handle(applicationException, applicationException.getErrorEnum(), applicationException.getErrorMsg());
    }
}