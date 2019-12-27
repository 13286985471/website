package it.world.auth.controller;

import it.world.auth.entity.SysUser;
import it.world.auth.feign.AuthFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SysAuthCtrl {
    private final String PREFIX = "pages/";

    @Autowired
    AuthFeign authFeign;

    @RequestMapping("/userlogin")
    public String loginPage() {
        return PREFIX+"login";
    }


    @RequestMapping("/level1/{username}")
    @ResponseBody
    public SysUser queryUser1(@PathVariable("username") String username){
        return authFeign.queryUser(username);
    }

}
