package it.world.auth.service;


import it.world.common.bean.entity.SysUser;

public interface SysUserService {
    SysUser findUserByName(String username);
}
