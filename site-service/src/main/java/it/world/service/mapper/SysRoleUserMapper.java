package it.world.service.mapper;


import it.world.common.entity.SysRoleUser;
import it.world.common.entity.SysUser;

public interface SysRoleUserMapper {
    SysRoleUser findRoleIdsByUserId(SysUser user);
}
