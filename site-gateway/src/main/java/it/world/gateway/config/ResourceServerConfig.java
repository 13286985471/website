package it.world.gateway.config;

import it.world.common.dataDic.IgnoreUrls;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

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
    private static final String RESOURCE_ID;

    static {
        RESOURCE_ID = "order";
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers(IgnoreUrls.url)//不需要权限认证的url
                .permitAll().anyRequest().authenticated()
                .and()
                .csrf().disable()//关闭跨站请求防护
                .httpBasic();
    }

}

