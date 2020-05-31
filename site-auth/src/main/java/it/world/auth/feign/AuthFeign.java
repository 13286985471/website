package it.world.auth.feign;


import it.world.common.entity.SysPermission;
import it.world.common.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value="SITE-SERVICE")
public interface AuthFeign {
    //查找所有权限信息
    @RequestMapping(value="/userInfo/queryPms")
    List<SysPermission> queryAllPermission();

    //查找用户信息
    @RequestMapping(value="/userInfo/queryUser/{username}")
    SysUser queryUser(@PathVariable("username") String name);
}
