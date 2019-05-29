package com.ggiit.easyblog.framework.web.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 结果返回实体
 *
 * @author gao
 * @date 2019.5.6
 */
@Getter
@Setter
public class ApiResult {

    /**
     * 状态码
     */
    private int code;
    /**
     * 对错误的具体解释
     */
    private String message;
    /**
     * 返回的结果包装在data中，data可以是单个对象
     */
    private Object data;

    /**
     * 默认构造
     */
    public ApiResult() {
        this.code = 200;
        this.message = "成功";
        this.data = null;
    }

    /**
     * 初始化一个新创建的 ApiResult 对象
     *
     * @param code    状态码
     * @param message 返回信息
     * @param data   数据对象
     */
    public ApiResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ApiResult success() {
        return ApiResult.success("成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ApiResult success(Object data) {
        return ApiResult.success("成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param message 返回内容
     * @return 成功消息
     */
    public static ApiResult success(String message) {
        return ApiResult.success(message, null);
    }

    /**
     * 返回成功消息
     *
     * @param message 返回内容
     * @param data   数据对象
     * @return 成功消息
     */
    public static ApiResult success(String message, Object data) {
        return new ApiResult(200, message, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static ApiResult error() {
        return ApiResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param message 返回内容
     * @return 警告消息
     */
    public static ApiResult error(int code, String message) {
        return new ApiResult(code, message, null);
    }

    /**
     * 返回错误消息
     *
     * @param message 返回内容
     * @return 警告消息
     */
    public static ApiResult error(String message) {
        return ApiResult.error(message, null);
    }


    /**
     * 返回错误消息
     *
     * @param message 返回内容
     * @param data   数据对象
     * @return 警告消息
     */
    public static ApiResult error(String message, Object data) {
        return new ApiResult(500, message, data);
    }
}
