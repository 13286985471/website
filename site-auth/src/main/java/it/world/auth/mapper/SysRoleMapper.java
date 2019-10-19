package it.world.auth.mapper;

import it.world.auth.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    List<SysRole> findRoleById(@Param("id") Integer id);
}
