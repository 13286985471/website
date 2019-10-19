package it.world.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {
    private static final String CLIEN_ID_ONE = "site_zuul";  //客户端1 用来标识客户的Id
    private static final String CLIENT_SECRET = "secret";   //secret客户端安全码
    private static final String GRANT_TYPE_PASSWORD = "password";   // 密码模式授权模式
    private static final String AUTHORIZATION_CODE = "authorization_code"; //授权码模式  授权码模式使用到了回调地址，是最为复杂的方式，通常网站中经常出现的微博，qq第三方登录，都会采用这个形式。
    private static final String REFRESH_TOKEN = "refresh_token";  //
    private static final String IMPLICIT = "implicit"; //简化授权模式
    private static final String GRANT_TYPE = "client_credentials";  //客户端模式
    private static final String SCOPE_READ = "read";
    private static final String SCOPE_WRITE = "write";
    private static final String TRUST = "trust";
    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;          //
    private static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;        //
    private static final String RESOURCE_ID = "*";    //指定哪些资源是需要授权验证的

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*授权码模式（authorization code 即先登录获取code,再获取token）
        简化模式（implicit 在redirect_uri 的Hash传递token; Auth客户端运行在浏览器中,如JS,Flash）
        密码模式（ password 将用户名,密码传过去,直接获取token）
        客户端模式（client credentials 无用户,用户向客户端注册,然后客户端以自己的名义向’服务端’获取资源）*/

        clients
                .inMemory()
                .withClient(CLIEN_ID_ONE)    //client_id用来标识客户的Id  客户端1
                .resourceIds(RESOURCE_ID)   //指定哪些资源是需要授权验证的
                .secret(CLIENT_SECRET)      //secret客户端安全码
                .scopes(SCOPE_WRITE, SCOPE_READ).autoApprove(true)  //允许授权范围
                .authorities("oauth2")      //客户端可以使用的权限
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)   //token 时间秒
                .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS)//刷新token 时间 秒
                //允许授权类型
                .authorizedGrantTypes(IMPLICIT,REFRESH_TOKEN,GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE);

                //.and().withClient(CLIEN_ID_two)   可以接着写下一个client
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(jwtTokenStore())
                .userDetailsService(userDetailsService)
                .tokenEnhancer(jwtTokenConverter())
                .authenticationManager(authenticationManager);
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtTokenConverter());
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("springcloud123");
        return converter;
    }

}
