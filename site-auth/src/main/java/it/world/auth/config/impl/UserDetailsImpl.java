package it.world.auth.config.impl;


import it.world.auth.entity.SysPermission;
import it.world.auth.entity.SysRole;
import it.world.auth.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * 写入权限
 */
public class UserDetailsImpl extends SysUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    public UserDetailsImpl(SysUser user) {
        if (user != null) {
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setRoles(user.getRoles());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (this.getUsername() != null) {
            //获取用户的角色集合
            List<SysRole> roles = this.getRoles();

            //遍历角色集合，并获取每个角色拥有的权限
            Set<String> pms=new HashSet();
            for (SysRole role : roles) {
                List<SysPermission> permissions = role.getSysPermissions();
                for (SysPermission permission :permissions) {
                    //为每个授权中心对象写入权限名
                    //在Security看来角色和权限是一样的，它认证的时候，把所有权限（角色、权限）都取出来，而不是分开验证。
                    //Security角色和权限共用authorities表
                    //此时我还是给角色权限分开两个表，这样表可以通用shiro框架，此时hasRole()表达式对应权限名
                    //分表后，权限名需加ROLE_开头，
                    // 实际上操作的时候hasRole表达式，会判断参数是否包含"ROLE_"前缀，如果没有则加上去，然后再去校验。有这个前缀则直接校验。
                    pms.add(permission.getPmName());
                }
            }
            if(!pms.isEmpty()){
                for(String pm:pms){
                    authorities.add(new SimpleGrantedAuthority(pm));
                }
            }
            return authorities;
        }
        return null;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
