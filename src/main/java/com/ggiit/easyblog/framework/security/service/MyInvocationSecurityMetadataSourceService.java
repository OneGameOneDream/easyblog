package com.ggiit.easyblog.framework.security.service;

import cn.hutool.core.util.StrUtil;
import com.ggiit.easyblog.common.constant.WebKeys;
import com.ggiit.easyblog.project.system.menu.entity.Menu;
import com.ggiit.easyblog.project.system.menu.service.MenuService;
import com.ggiit.easyblog.project.system.role.entity.Role;
import com.ggiit.easyblog.project.system.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 加载资源与权限的对应关系
 *
 * @author gao
 * @date 2019.5.17
 * 实现FilterInvocationSecurityMetadataSource接口也是必须的。 首先，这里从数据库中获取信息。 其中loadResourceDefine方法不是必须的，
 * 这个只是加载所有的资源与权限的对应关系并缓存起来，避免每次获取权限都访问数据库（提高性能），然后getAttributes根据参数（被拦截url）返回权限集合。
 * 这种缓存的实现其实有一个缺点，因为loadResourceDefine方法是放在构造器上调用的，而这个类的实例化只在web服务器启动时调用一次，那就是说loadResourceDefine方法只会调用一次，
 * 如果资源和权限的对应关系在启动后发生了改变，那么缓存起来的权限数据就和实际授权数据不一致，那就会授权错误了。但如果资源和权限对应关系是不会改变的，这种方法性能会好很多。
 * 要想解决 权限数据的一致性 可以直接在getAttributes方法里面调用数据库操作获取权限数据，通过被拦截url获取数据库中的所有权限，封装成Collection<ConfigAttribute>返回就行了。（灵活、简单
 * 启动加载顺序：1：调用loadResourceDefine()方法  2：调用supports()方法   3：调用getAllConfigAttributes()方法
 */
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    //存放资源配置对象
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    /**
     * @param o 参数是要访问的url，返回这个url对于的所有权限（或角色）
     *          每次请求后台就会调用 得到请求所拥有的权限
     *          这个方法在url请求时才会调用，服务器启动时不会执行这个方法
     *          getAttributes这个方法会根据你的请求路径去获取这个路径应该是有哪些权限才可以去访问。
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }


    /**
     * 初始化资源 ,提取系统中的所有权限，加载所有url和权限（或角色）的对应关系，  web容器启动就会执行
     * 如果启动@PostConstruct 注解   则web容器启动就会执行
     */
    //@PostConstruct
    public void loadResourceDefine() {
        // if (resourceMap == null) {
        //应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
        resourceMap = new ConcurrentHashMap<>();
        //容器启动时,获取全部系统菜单资源信息
        List<Menu> menuList = this.menuService.findList();
        if (!CollectionUtils.isEmpty(menuList)) {
            for (Menu menu : menuList) {
                //请求url
                String url = StrUtil.isNotBlank(menu.getUrl()) ? menu.getUrl().trim() : "";
                // 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
                if (resourceMap.containsKey(url)) {
                    Collection<ConfigAttribute> value = resourceMap.get(url);
                    value.add(configAttributes);
                    resourceMap.put(url, value);
                } else {
                    Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                    atts.add(configAttributes);
                    resourceMap.put(url, atts);
                }
            }
        }

        //  }
    }


}

