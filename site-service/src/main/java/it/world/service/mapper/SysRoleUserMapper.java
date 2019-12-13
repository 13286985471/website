package it.world.service.mapper;


import it.world.service.entity.SysRoleUser;
import it.world.service.entity.SysUser;

public interface SysRoleUserMapper {
    SysRoleUser findRoleIdsByUserId(SysUser user);
}
