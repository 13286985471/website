package it.world.service.service;


import it.world.service.entity.SysRoleUser;
import it.world.service.entity.SysUser;

public interface SysRoleUserService {
    SysRoleUser findRoleIdsByUserId(SysUser user);
}
