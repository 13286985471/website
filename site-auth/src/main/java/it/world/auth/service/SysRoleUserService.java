package it.world.auth.service;


import it.world.auth.entity.SysRoleUser;
import it.world.auth.entity.SysUser;


public interface SysRoleUserService {
    SysRoleUser findRoleIdsByUserId(SysUser user);
}
