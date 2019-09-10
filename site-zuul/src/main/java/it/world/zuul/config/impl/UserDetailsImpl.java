package it.world.zuul.config.impl;

import it.world.zuul.entity.SysPermission;
import it.world.zuul.entity.SysRole;
import it.world.zuul.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

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
