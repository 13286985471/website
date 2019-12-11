package it.world.auth.config;

import it.world.auth.common.IgnoreUrls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig {
    private static final String DEMO_RESOURCE_ID = "order";
    @Autowired
    private  LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private  AuthenticationFailureHandler loginFailureHandler;
    @Autowired
    private  AuthenticationSuccessHandler loginSuccessHandler;

    @Configuration
    @EnableResourceServer
    protected  class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .authorizeRequests().antMatchers(IgnoreUrls.url)//不需要权限认证的url
                    .permitAll().anyRequest().authenticated()
                    .and()
                    .formLogin().successHandler(loginSuccessHandler).failureHandler(loginFailureHandler)
                    //.loginProcessingUrl("/authentication/form")//登录需要经过的url请求
                    .and()
                    .logout().logoutSuccessHandler(logoutSuccessHandler)
                    .and()
                    .csrf().disable()//关闭跨站请求防护
                    .httpBasic();
            // @formatter:on
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        private UserDetailsService userDetailsService;


        /*@Autowired
        RedisConnectionFactory redisConnectionFactory;*/

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            //配置两个客户端,一个用于password认证一个用于client认证
            clients.inMemory()
                    .withClient("client_1")//客户端
                    .secret("{noop}123456")//密钥
                    .resourceIds(DEMO_RESOURCE_ID)//资源id
                    .authorizedGrantTypes("client_credentials", "refresh_token")//密码授权模式和刷新令牌
                    .accessTokenValiditySeconds(72000) //有效时间 2小时
                    .scopes("select")//授权范围
                    .authorities("client")
                 .and()
                    .withClient("client_2")
                    .resourceIds(DEMO_RESOURCE_ID)
                    .authorizedGrantTypes("password", "refresh_token")
                    .accessTokenValiditySeconds(72000)
                    .refreshTokenValiditySeconds(144000)
                    .scopes("select")
                    .authorities("client")
                    .secret("{noop}456");
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    /*.tokenStore(new RedisTokenStore(redisConnectionFactory))*/
                    .authenticationManager(authenticationManager)
                    .userDetailsService(userDetailsService);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            //允许表单认证
            oauthServer.allowFormAuthenticationForClients();
        }



    }
}
