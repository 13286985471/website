package it.world.service.controller;


import it.world.common.unified.RespBody;
import it.world.service.entity.SysPermission;
import it.world.service.entity.SysUser;
import it.world.service.service.SysPermissionService;
import it.world.service.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 用户信息
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoCtrl {
    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/queryPms")
    public List<SysPermission> queryPms() {
        return sysPermissionService.findAll();
    }

    @RequestMapping("/queryUser/{username}")
    public SysUser queryUser(@PathVariable("username") String username) {
        return sysUserService.findUserByName(username);
    }
    @RequestMapping("/register")
    public RespBody register(@RequestBody SysUser user) {
        Integer id = sysUserService.insUser(user);
        RespBody respBody= new RespBody(id);
        return respBody;
    }
}
