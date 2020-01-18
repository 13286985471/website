package it.world.gateway.config.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.world.common.unified.RespBody;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限不足异常类重写
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) {
        RespBody respBody=new RespBody(false,HttpServletResponse.SC_UNAUTHORIZED,"没有权限");
        response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(response.getOutputStream(), respBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
