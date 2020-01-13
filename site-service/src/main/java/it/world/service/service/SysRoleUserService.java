package it.world.service.service;


import it.world.common.entity.SysRoleUser;
import it.world.common.entity.SysUser;

public interface SysRoleUserService {
    SysRoleUser findRoleIdsByUserId(SysUser user);
}
