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
    /**
     * 成功
     */
    SUCCESS(200, "成功"),

    /**
     * 权限不足
     */
    NO_PERMISSION(211, "权限不足"),
    /**
     * 服务器异常
     */
    SERVER_ERROR(10000, "服务器异常"),
    /**
     * 认证失败
     */
    AUTH_ERROR(10001, "认证失败"),
    /**
     * 参数错误
     */
    PARAMS_ERROR(10002, "参数错误"),
    /**
     * Json解析错误
     */
    JSON_PARSE_ERROR(10003, "Json解析错误"),
    /**
     * 非法字符串
     */
    ILLEAGAL_STRING(15001, "非法字符串"),
    /**
     * 未知错误
     */
    UNKNOW_ERROR(16000, "未知错误"),

    /**
     * 用戶名已存在错误
     */
    USERNAME_EXIST(17000, "用户名已存在"),
    /**
     * 角色名已存在
     */
    ROLE_NAME_EXIST(17001, "角色名已存在"),
    /**
     * 菜单名已存在
     */
    MENU_NAME_EXIST(17002, "菜单名已存在"),
    /**
     * Email已存在
     */
    EMAIL_EXIST(17003, "Email已存在"),
    /**
     * 用户名不存在
     */
    USER_NOT_FOUND(17004, "用户名不存在"),
    /**
     * 用户名不存在
     */
    INIT_JWT_USER_ERROR(17004, "初始化JwtUser失败");


    private int code;
    private String msg;


    /**
     * 获取响应信息
     *
     * @return 响应信息
     */
    public static String getMes(int code) {
        String mes = "获取响应信息失败";
        for (ResponseCode responseCode : ResponseCode.values()) {
            if (responseCode.getCode() == code) {
                mes = responseCode.getMsg();
            }
        }
        return mes;
    }

}
