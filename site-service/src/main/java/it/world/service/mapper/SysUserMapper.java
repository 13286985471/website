package it.world.service.mapper;


import it.world.service.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper{
    SysUser findByUsername(@Param("username") String username);
    Integer insertUser(SysUser user);
}
