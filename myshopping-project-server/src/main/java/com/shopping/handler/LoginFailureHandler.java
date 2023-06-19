package com.shopping.handler;

import com.alibaba.fastjson.JSONObject;
import com.shopping.commons.Result;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理器
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        //根据登录失败的异常确定不同的失败信息
        String errMSG = "";
        if(exception.getClass()== UsernameNotFoundException.class){
            errMSG = "用户名不存在";
        }else if(exception.getClass() == BadCredentialsException.class){
            errMSG = "密码错误";
        }else if(exception.getClass()== LockedException.class){
            errMSG = "账户被冻结";
        }
        //设置响应类型及编码集
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(JSONObject.toJSON(Result.fail(401,errMSG)));
    }
}
