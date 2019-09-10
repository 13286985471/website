package it.world.zuul.config.impl;

import com.alibaba.fastjson.JSONObject;
import it.world.zuul.entity.SysUser;
import it.world.zuul.mapper.AuthFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AuthFeign authFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String  userInfo = authFeign.queryUser(username);
        if (StringUtils.isEmpty(userInfo)){
            return null;
        }
        JSONObject jsonUser = JSONObject.parseObject(userInfo);
        SysUser user = JSONObject.toJavaObject(jsonUser, SysUser.class);
        return new UserDetailsImpl(user);
    }
}
