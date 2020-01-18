package it.world.service.controller;


import it.world.common.unified.RespBody;
import it.world.common.entity.SysPermission;
import it.world.common.entity.SysUser;
import it.world.common.entity.SysUserInfo;
import it.world.service.service.SysPermissionService;
import it.world.service.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public RespBody register(@RequestBody SysUser sysUser,@RequestBody SysUserInfo sysUserInfo) {
        RespBody respBody= new RespBody();
        respBody.setData(sysUser,sysUserInfo);
        return respBody;
    }
}
