package com.ggiit.easyblog.common.constant;

/**
 * 系统相关产量
 *
 * @author gao
 * @date 2019.4.25
 */
public class WebKeys {

    /*************** 用户相关 *************/
    /**
     * 登录用户信息
     */
    public final static String USER_KEY = "userInfo";
    /**
     * 登录用户名
     */
    public final static String USERNAME_KEY = "username";
    /**
     * 登录时间
     */
    public final static String USER_LOGIN_DATE_KEY = "loginDate";
    /**
     * 登录IP
     */
    public final static String USER_LOGIN_IP_KEY = "loginIp";
    /**
     * 登录用户权限set
     */
    public final static String USER_PERMISSION_KEY = "permissionSet";
    /**
     * 删除标识true:正常false:删除
     */
    public static final Boolean DEL_FLAG_NORMAL = true;
    /**
     * 删除标识true:正常false:删除
     */
    public static final Boolean DEL_FLAG_DELETE = false;

    /**
     * 账号状态true:启用false:禁用
     */
    public static final Boolean USER_STATUS_NORMAL = true;
    /**
     * 账号状态true:启用false:禁用
     */
    public static final Boolean USER_STATUS_LOCKED = false;
    /*************** 用户相关End *************/
    /***************日志相关****************/
    /**
     * 方法执行成功
     */
    public static final String METHOD_SUCCESS = "成功";
    /**
     * 方法执行失败
     */
    public static final String METHOD_FAIL = "失败";

    /**************************************/

}
