package it.world.auth.mapper;

import it.world.auth.entity.SysRoleUser;
import it.world.auth.entity.SysUser;

public interface SysRoleUserMapper {
    SysRoleUser findRoleIdsByUserId(SysUser user);
}
