package com.ggiit.easyblog.framework.web.entity;

import com.ggiit.easyblog.common.constant.ResponseCode;
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
     * 返回的结果包装在value中，value可以是单个对象
     */
    private Object value;

    /**
     * 默认构造
     */
    public ApiResult() {
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMsg();
        this.value = null;
    }

    /**
     * 初始化一个新创建的 ApiResult 对象
     *
     * @param code    状态码
     * @param message 返回信息
     * @param value   数据对象
     */
    public ApiResult(int code, String message, Object value) {
        this.code = code;
        this.message = message;
        this.value = value;
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
    public static ApiResult success(Object value) {
        return ApiResult.success("成功", value);
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
     * @param value   数据对象
     * @return 成功消息
     */
    public static ApiResult success(String message, Object value) {
        return new ApiResult(200, message, value);
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
    public static ApiResult error(String message) {
        return ApiResult.error(message, null);
    }


    /**
     * 返回错误消息
     *
     * @param message 返回内容
     * @param value   数据对象
     * @return 警告消息
     */
    public static ApiResult error(String message, Object value) {
        return new ApiResult(500, message, value);
    }
}
