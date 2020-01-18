package it.world.gateway.config.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.world.common.unified.RespBody;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token无效异常重写
 */
@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        RespBody respBody=new RespBody(false,HttpServletResponse.SC_UNAUTHORIZED);
        try {
            if(authException.getCause() instanceof InvalidTokenException){
                respBody.setMsg("该token无效");
                mapper.writeValue(response.getOutputStream(), respBody);
            }else {
                respBody.setMsg("访问此资源需要身份验证");
                mapper.writeValue(response.getOutputStream(), respBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException();
        }

    }
}
