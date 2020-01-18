package it.world.service.service;


import it.world.service.entity.SysUser;

public interface SysUserService {
    SysUser findUserByName(String username);
    Integer insUser(SysUser sysUser);
}
