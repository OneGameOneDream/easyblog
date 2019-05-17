package com.ggiit.easyblog.framework.security.config;

import cn.hutool.core.util.StrUtil;
import com.ggiit.easyblog.project.system.menu.entity.Menu;
import com.ggiit.easyblog.project.system.menu.service.MenuService;
import com.ggiit.easyblog.project.system.role.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.*;

/**
 * 资源权限管理器
 * 为权限决断器提供支持
 *
 * @author gao
 * @date 2019.5.17
 */
@Component
@Slf4j
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /**
     * 菜单业务对象
     */
    @Autowired
    private MenuService menuService;

    /**
     * 权限键值对对象(key:访问的url  value:需要的权限)
     */
    private Map<String, Collection<ConfigAttribute>> map = null;


    /**
     * 加载权限表中所有操作请求权限
     */
    public void loadResourceDefine() {
        map = new HashMap<>(16);
        Collection<ConfigAttribute> collection;
        ConfigAttribute configAttribute;
        // 获取启用的权限操作请求
        List<Menu> menuList = menuService.findList();
        for (Menu menu : menuList) {
            if (StrUtil.isNotBlank(menu.getPermission()) && StrUtil.isNotBlank(menu.getUrl())) {
                collection = new ArrayList<>();
                configAttribute = new SecurityConfig(menu.getPermission());
                //作为MyAccessDecisionManager类的decide的第三个参数
                collection.add(configAttribute);
                //用权限的path作为map的key，用collection的集合作为value
                map.put(menu.getUrl(), collection);
            }
        }
    }

    /**
     * 判定用户请求的url是否在权限表中
     * 如果在权限表中，则返回给decide方法，用来判定用户是否有此权限
     * 如果不在权限表中则放行
     *
     * @param o Object中包含用户请求request
     * @return 需要的权限集合
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (map == null) {
            loadResourceDefine();
        }
        //Object中包含用户请求request
        String url = ((FilterInvocation) o).getRequestUrl();
        PathMatcher pathMatcher = new AntPathMatcher();
        Iterator<String> iterator = map.keySet().iterator();
        //遍历所有权限,取url
        while (iterator.hasNext()) {
            String resURL = iterator.next();
            if (StrUtil.isNotBlank(resURL) && pathMatcher.match(resURL, url)) {
                return map.get(resURL);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
