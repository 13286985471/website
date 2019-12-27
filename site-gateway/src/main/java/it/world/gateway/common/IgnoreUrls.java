package it.world.gateway.common;

/**
 *不需要登录授权的url
 */
public interface IgnoreUrls {
    String[] url = {
            "/",
            "/userlogin",
            "/error",
            "/authentication/form",
            "/adp",
            "/login",
            "/auth/**"
    };
}
