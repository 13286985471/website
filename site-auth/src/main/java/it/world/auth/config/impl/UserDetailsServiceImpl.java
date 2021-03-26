package it.world.auth.config.impl;


import it.world.auth.service.SysUserService;
import it.world.common.bean.entity.SysUser;
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
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.findUserByName(username);
        if (user==null){
            throw new UsernameNotFoundException("该用户不存在");
        }
        return new UserDetailsImpl(user);
    }
}
