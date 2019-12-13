package it.world.auth.controller;

import it.world.auth.entity.SysPermission;
import it.world.auth.entity.SysUser;
import it.world.auth.service.SysPermissionService;
import it.world.auth.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SysAuthCtrl {
    private final String PREFIX = "pages/";
    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysPermissionService sysPermissionService;

    @RequestMapping("/userlogin")
    public String loginPage() {
        return PREFIX+"login";
    }
    @RequestMapping("/level2/{username}")
    @ResponseBody
    public SysUser queryUser(@PathVariable("username") String username){
        return sysUserService.findUserByName(username);
    }

    @RequestMapping("/level1/{username}")
    @ResponseBody
    public SysUser queryUser1(@PathVariable("username") String username){
        return sysUserService.findUserByName(username);
    }

    @PreAuthorize("hasAuthority('ROLE_LEVEL1')")
    @RequestMapping("/order/{username}")
    @ResponseBody
    public SysUser order(@PathVariable("username") String username){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return sysUserService.findUserByName(username);
    }

    @RequestMapping("/auth/queryAllSPM")
    @ResponseBody
    public List<SysPermission> queryAllSPM(){
        return sysPermissionService.findAll();
    }
}
