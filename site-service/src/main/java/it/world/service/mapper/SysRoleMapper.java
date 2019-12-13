package it.world.service.mapper;


import it.world.service.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    List<SysRole> findRoleById(@Param("id") Integer id);
}
