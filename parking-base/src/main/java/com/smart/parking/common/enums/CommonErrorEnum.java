package com.smart.parking.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Locale;

@AllArgsConstructor
@Getter
public enum CommonErrorEnum implements ErrorEnum {
    /**
     * 通用错误 1-2位00
     */
    SUCCESS("000000", "成功"),

    UNKNOWN_ERROR("000001", "未知错误, 请联系技术人员"),
    METHOD_NOT_IMPLEMENTED("000002", "该方法暂无实现"),
    PARAM_TYPE_INVALID("000003", "参数类型不合法"),
    PARAM_ABSENT("000004", "缺少必要参数"),
    PARAM_INVALID("000005", "参数不合法"),
    SIGN_INVALID("000006", "验签失败"),
    HTTP_REQUEST_METHOD_INVALID("000007", "http请求方式不支持"),
    FORBIDDEN("000008", "无权限"),
    QUERY_TIME_RANGE_INVALID("000009", "查询时间范围不合法"),

    /**
     * 系统错误 1-2位01
     */

    /**
     * 数据库相关错误 3-4位00
     */
    DB_ERROR("010000", "数据库错误"),

    /**
     * 网络相关错误 3-4位01
     */
    NETWORK_TIME_OUT("010100", "网络超时"),

    /**
     * 服务器配置相关错误 3-4位02
     */
    SEQUENCE_ERROR("010200", "the clock moved backwards", "系统时钟回拨错误"),

    CONFIG_FILE_NOT_FOUND("010201", "找不到配置文件"),

    CONFIG_SETTINGS_NOT_FOUND("010202", "找不到配置项"),

    /**
     * 下游系统异常 3-4位03
     */
    DOWNSTREAM_SYSTEM_BUSY("010300", "系统繁忙"),

    TOKEN_INVALID("010301", "token异常"),

    DOWNSTREAM_SYSTEM_ERROR("010302", "系统错误");

    String value;
    String message;
    String showMessage;

    CommonErrorEnum(String value, String showMessage) {
        this(value, null, showMessage);
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getDetailMessage() {
        return ErrorEnum.super.getDetailMessage();
    }

    @Override
    public String getShowMessage(Locale locale) {
        return ErrorEnum.super.getShowMessage(locale);
    }
}
