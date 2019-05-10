package com.ggiit.easyblog.common.util.security;


import com.ggiit.easyblog.common.constant.WebKeys;
import com.ggiit.easyblog.project.system.user.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用户工具类
 *
 * @author gao
 * @date 2019.5.8
 */
public class UserUtils {

    /**
     * 获取当前用户对象
     */
    public static User getUser() {
        User user = new User();
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            //如果未登录，将其昵称设置为匿名用户
            user.setNickname(WebKeys.ANONYMOUS_USER);
        }
        return user;
    }

}
