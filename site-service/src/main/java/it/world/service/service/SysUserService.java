package it.world.service.service;


import it.world.common.bean.entity.SysUser;

public interface SysUserService {
    SysUser findUserByName(String username);
    Integer insUser(SysUser sysUser);
}
