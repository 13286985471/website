package it.world.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebSecurityHandlerConfig {


    /**
     * 登出成功
     * @return
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                Map m = new HashMap();
                m.put("rcd","0");
                m.put("msg","退出成功");
                String json = new ObjectMapper().writeValueAsString(m);

                out.write(json);
                out.flush();
                out.close();
            }
        };
    }

    /**
     * 登陆失败，返回401
     */
    @Bean
    public AuthenticationFailureHandler loginFailureHandler() {

        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse, AuthenticationException e)
                    throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();

                Map m = new HashMap();
                m.put("rcd","-1");


                if (e instanceof BadCredentialsException) {
                    m.put("msg","用户名或密码错误");
                    // httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                } else {
                    m.put("msg",e.getMessage());
                }
                String json = new ObjectMapper().writeValueAsString(m);
                out.write(json);
            }
        };
    }

    /**
     * 登陆成功
     */
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse, Authentication authentication)
                    throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                out.write(resultOk(authentication));
                out.flush();
                out.close();
            }
        };
    }


    private String resultOk(Authentication authentication) {
        String userJson = "";
        Map m = new HashMap();
        // exclude/include whatever fields you need
        try {
            m.put("ec", "0");
            UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
            auth.getPrincipal();
            userJson = new ObjectMapper().writeValueAsString(m);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return userJson;

    }
}
