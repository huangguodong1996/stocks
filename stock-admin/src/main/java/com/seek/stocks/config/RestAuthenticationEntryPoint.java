package com.seek.stocks.config;

import cn.hutool.json.JSONUtil;
import com.seek.stocks.exception.ResultBody;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败后处理
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * 返回json格式
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(ResultBody.unauthorized(authException.getMessage())));
        response.getWriter().flush();
    }
}
