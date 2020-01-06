package it.world.gateway.config;

import it.world.gateway.common.IgnoreUrls;
import it.world.gateway.feign.AuthFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 此处拦截，是为了对url进行权限检验
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthFeign authFeign;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().antMatchers(IgnoreUrls.url)//不需要权限认证的url
            .permitAll().anyRequest().authenticated()
            .and()
            .csrf().disable()//关闭跨站请求防护
            .httpBasic();
    }
}
