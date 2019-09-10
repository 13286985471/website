package it.world.zuul.mapper;

import it.world.zuul.entity.SysPermission;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(value="SITE-AUTH")
public interface AuthFeign {
    //查找用户
    @RequestMapping(value="/auth/{username}")
    String queryUser(@PathVariable("username") String name);

    //查询权限
    @RequestMapping(value="/auth/queryAllSPM")
    String  queryAllSPM();
}
