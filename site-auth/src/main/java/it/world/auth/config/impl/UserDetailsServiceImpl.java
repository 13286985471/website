package it.world.auth.config.impl;


import it.world.auth.feign.AuthFeign;
import it.world.common.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户信息来源
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AuthFeign authFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = authFeign.queryUser(username);
        if (user==null){
            return null;
        }
        return new UserDetailsImpl(user);
    }
}
