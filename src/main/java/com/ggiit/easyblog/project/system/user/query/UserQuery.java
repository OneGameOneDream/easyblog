package com.ggiit.easyblog.project.system.user.query;

import cn.hutool.core.util.StrUtil;
import com.ggiit.easyblog.common.constant.WebKeys;
import com.ggiit.easyblog.project.system.user.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户查询器
 *
 * @author gao
 * @date 2019.4.29
 */
@Component
public class UserQuery {
    /**
     * 获取用户查询条件对象
     *
     * @param user 用户对象
     * @return Specification 用户查询条件对象
     */
    public Specification<User> getListSpecification(User user) {
        // Specification查询构造器
        return (root, query, cb) -> {
            //用于暂时存放查询条件的集合
            List<Predicate> predicatesList = new ArrayList<>();
            //--------------------------------------------
            //查询条件
            //默认查询未删除用户
            if (user.getDelFlag() != null) {
                Predicate delFlagPredicate = cb.equal(root.get("delFlag"), user.getDelFlag());
                predicatesList.add(delFlagPredicate);
            } else {
                Predicate delFlagPredicate = cb.equal(root.get("delFlag"), WebKeys.DEL_FLAG_NORMAL);
                predicatesList.add(delFlagPredicate);
            }
            //用户名
            if (!StrUtil.isBlank(user.getUsername())) {
                Predicate usernamePredicate = cb.like(root.get("username"), '%' + user.getUsername() + '%');
                predicatesList.add(usernamePredicate);
            }
            //手机
            if (!StrUtil.isBlank(user.getPhone())) {
                Predicate phonePredicate = cb.like(root.get("phone"), '%' + user.getPhone() + '%');
                predicatesList.add(phonePredicate);
            }
            //电子邮箱
            if (!StrUtil.isBlank(user.getEmail())) {
                Predicate emailPredicate = cb.like(root.get("email"), '%' + user.getEmail() + '%');
                predicatesList.add(emailPredicate);
            }
            //用户状态
            if (user.getState() != null) {
                Predicate statePredicate = cb.equal(root.get("state"), user.getState());
                predicatesList.add(statePredicate);
            }
            //用户昵称
            if (user.getNickname() != null) {
                Predicate nickPredicate = cb.equal(root.get("nickname"), user.getNickname());
                predicatesList.add(nickPredicate);
            }
            //最终将查询条件拼好然后return
            Predicate[] predicates = new Predicate[predicatesList.size()];
            return cb.and(predicatesList.toArray(predicates));
        };
    }

}
