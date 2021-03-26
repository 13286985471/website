package it.world.auth.mapper;


import it.world.common.bean.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    List<SysRole> findRoleById(@Param("id") Integer id);
}
