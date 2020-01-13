package it.world.service.service;


import it.world.common.entity.SysUser;

public interface SysUserService {
    SysUser findUserByName(String username);
}
