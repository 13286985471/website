package it.world.service.mapper;


import it.world.common.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    List<SysRole> findRoleById(@Param("id") Integer id);
}
