package com.ggiit.easyblog.framework.jwt.entity;

import com.ggiit.easyblog.project.system.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser extends User implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return super.getAuthorities();
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> auths) {
        this.authorities = auths;
    }

    


}
