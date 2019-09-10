package it.world.auth.mapper;

import it.world.auth.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper {
    SysUser findByUsername(@Param("username") String username);
}
