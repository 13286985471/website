package it.world.auth.controller;

import it.world.auth.entity.SysPermission;
import it.world.auth.entity.SysUser;
import it.world.auth.service.SysPermissionService;
import it.world.auth.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SysAuthCtrl {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysPermissionService sysPermissionService;

    @RequestMapping("/auth/{username}")
    public SysUser queryUser(@PathVariable("username") String username){
        return sysUserService.findByUsername(username);
    }

    @RequestMapping("/auth/queryAllSPM")
    public List<SysPermission> queryAllSPM(){
        return sysPermissionService.findAll();
    }
}
