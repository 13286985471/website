package it.world.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SysAuthCtrl {
    private final String PREFIX = "pages/";

    @RequestMapping("/userlogin")
    public String loginPage() {
        return PREFIX+"login";
    }
}
