package com.longi.dap.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        String error = "";
        if (e instanceof LockedException) {
            error = "账户被锁定，请联系管理员！";
        } else if (e instanceof CredentialsExpiredException) {
            error = "密码过期，请联系管理员！";
        } else if (e instanceof AccountExpiredException) {
            error = "账户过期，请联系管理员！";
        } else if (e instanceof DisabledException) {
            error = "账户被禁用，请联系管理员！";
        } else if (e instanceof BadCredentialsException) {
            error = "用户名或密码输入错误，请重新输入！";
        }

        httpServletRequest.getSession().setAttribute("loginError", error);
        httpServletResponse.sendRedirect("/login?error=true");
    }
}
