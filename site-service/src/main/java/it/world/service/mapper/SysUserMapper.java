package it.world.service.mapper;


import it.world.common.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper{
    SysUser findByUsername(@Param("username") String username);
    Integer insertUser(SysUser user);
    Integer test1(@Param("username") String username,@Param("password") String password);
}
