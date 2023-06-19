package com.shopping.handler;

import com.alibaba.fastjson.JSONObject;
import com.shopping.commons.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出成功处理器
 */
public class ShoppingLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println(request.getSession().getAttribute("curUser")+"<====>session");
        System.out.println(authentication+"<=====>authentication");
        System.out.println(SecurityContextHolder.getContext()+"<=====>authentication");
        //设置响应类型及编码集
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(JSONObject.toJSON(Result.success("退出成功")));
    }
}
