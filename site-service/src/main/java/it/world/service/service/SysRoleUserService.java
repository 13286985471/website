package it.world.service.service;


import it.world.common.bean.entity.SysRoleUser;
import it.world.common.bean.entity.SysUser;

public interface SysRoleUserService {
    SysRoleUser findRoleIdsByUserId(SysUser user);
}
