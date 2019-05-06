package com.ggiit.easyblog.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应码
 *
 * @author gao
 * @date 2019.5.6
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS(200, "成功"),
    NO_PERMISSION(211, "权限不足"),
    SERVER_ERROR(10000, "服务器异常"),
    AUTH_ERROR(10001, "认证失败"),
    PARAMS_ERROR(10002, "参数错误"),
    JSON_PARSE_ERROR(10003, "Json解析错误"),
    ILLEAGAL_STRING(15001, "非法字符串"),
    UNKNOW_ERROR(16000, "未知错误");

    private int code;
    private String msg;
}
