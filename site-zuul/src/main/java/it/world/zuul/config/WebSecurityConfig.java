package it.world.zuul.config;

import com.alibaba.fastjson.JSON;
import it.world.zuul.common.IgnoreUrls;
import it.world.zuul.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //定制请求的授权规则
        http.authenticationProvider(authenticationProvider())
                //不需要登录授权的url(IgnoreUrls.url)
                .authorizeRequests().antMatchers(IgnoreUrls.url)
                .permitAll()
                //其他所有页面必须验证后才可以访问
                .and().authorizeRequests().anyRequest().authenticated()
                .and().httpBasic()
                //未登录时，进行json格式的提示
                .authenticationEntryPoint((request,response,authException) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    Map map=new HashMap();
                    map.put("message", "未登录");
                    map.put("operation","servlet");
                    map.put("state","0");
                    returnJson(response,map);
                });

        //开启自动配置的登录功能
        //1.login来到登录页,有默认登录页
        //2.登录失败重定向到login?error
        //3.loginProcessingUrl 在自定义登录页面后，这个验证登录的地址也需要自己定义
        http.formLogin().loginProcessingUrl("/authentication/form")
                //登录失败，返回json
                .failureHandler((request,response,ex) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    Map map = new HashMap();
                    map.put("operation","login");
                    map.put("state","0");
                    if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
                        map.put("message","用户名或密码错误");
                    } else if (ex instanceof DisabledException) {
                        map.put("message","账户被禁用");
                    } else {
                        map.put("message",ex.getMessage());
                    }
                    returnJson(response,map);
                })//登录成功，返回json
                .successHandler((request,response,authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_OK);
                    SysUser user=(SysUser) authentication.getPrincipal();
                    Map map = new HashMap();
                    map.put("message","登录成功");
                    map.put("operation","login");
                    map.put("state","1");
                    map.put("username",user.getUsername());
                    map.put("roles",user.getRoles());
                    logger.debug("用户登录:---->"+user.getUsername());
                    returnJson(response,map);
                })
                .and()
                .exceptionHandling()
                //没有权限，返回json
                .accessDeniedHandler((request,response,ex) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    Map map=new HashMap();
                    map.put("operation","servlet");
                    map.put("state","0");
                    map.put("message", "权限不足");
                    logger.debug("访问失败:---->"+ex.getMessage());
                    returnJson(response, map);
                })
                .and()
                .logout()
                //退出成功，返回json
                .logoutSuccessHandler((request,response,authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_OK);
                    SysUser user=(SysUser) authentication.getPrincipal();
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("operation","logout");
                    map.put("state","1");
                    map.put("message","退出成功");
                    logger.debug("用户退出:---->"+user.getUsername());
                    returnJson(response,map);
                });


        //开启自动配置的注销功能
        //注销后默认login?logout
        //.logoutSuccessUrl()可配置注销成功后跳转页
        //http.logout().logoutSuccessUrl("/");

        //权限拒绝页面
        //http.exceptionHandling().accessDeniedPage("/adp");
        http.rememberMe();

        //开启跨域访问
        http.cors().disable();
        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();
    }

    /**
     * 登录验证授权返回json信息
     * @param response
     * @param map
     * @throws IOException
     */
    public void returnJson(HttpServletResponse response,Map map) throws IOException {
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(map));
        out.flush();
        out.close();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }


    /**
     * 用户验证
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setHideUserNotFoundExceptions(false);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }


    @Override
    public void configure(WebSecurity web){
        //对于在header里面增加token等类似情况，放行所有OPTIONS请求。
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}

