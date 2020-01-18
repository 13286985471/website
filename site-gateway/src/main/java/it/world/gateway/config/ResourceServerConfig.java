package it.world.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 此处拦截，是为了对access_token进行检验。
 * RESOURCE_ID要与鉴权中心的RESOURCE_ID
 * 鉴权中心/oauth/check_token，该接口检验access_token
 *
 */
@Configuration
@EnableResourceServer
@EnableOAuth2Sso
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private Properties properties;
    @Autowired
    AccessDeniedHandler accessDeniedHandler;
    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;
    private static final String RESOURCE_ID;
    static {
        /*
         * 与鉴权中心一致
         */
        RESOURCE_ID = "order";
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(RESOURCE_ID).stateless(true)
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        /*
         * 传递 token 三种方式
         * 1.请求时添加Authorization header=> Authorization : Bearer xxxxx
         * 2.请求地址添加参数access_token=> /api/a?access_token=xxxxx
         * 3.cookie方式 添加access_token=> access_token=xxxxx
         */
        http
                .authorizeRequests().antMatchers(properties.ignoreUrls)//不需要权限认证的url
                .permitAll().anyRequest().authenticated()
                .and()
                .csrf().disable()//关闭跨站请求防护
                .httpBasic();
    }

}

