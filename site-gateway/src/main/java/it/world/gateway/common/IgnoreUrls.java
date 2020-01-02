package it.world.gateway.common;

/**
 *不需要授权的url
 */
public interface IgnoreUrls {
    String[] url = {
            "/",
            "/userlogin",
            "/error",
            "/authentication/form",
            "/adp",
            "/login",
            "/uaa/**",
            "/oauth/**"
    };
}
