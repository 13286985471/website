package it.world.auth.config;

import it.world.auth.common.IgnoreUrls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler loginFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
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
    }

    @Override
    public void configure(WebSecurity web){
        //对于在header里面增加token等类似情况，放行所有OPTIONS请求。
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**","/favor.ioc");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setHideUserNotFoundExceptions(false);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }
}

