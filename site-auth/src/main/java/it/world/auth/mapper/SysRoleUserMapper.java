package it.world.auth.mapper;


import it.world.common.bean.entity.SysRoleUser;
import it.world.common.bean.entity.SysUser;

public interface SysRoleUserMapper {
    SysRoleUser findRoleIdsByUserId(SysUser user);
}
