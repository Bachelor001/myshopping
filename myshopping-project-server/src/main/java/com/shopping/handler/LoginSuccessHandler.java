package com.shopping.handler;

import com.alibaba.fastjson.JSONObject;
import com.shopping.commons.CurUser;
import com.shopping.commons.Result;
import com.shopping.model.UserInfo;
import com.shopping.utils.JWTUtils;
import com.shopping.utils.RsaUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功处理器，该处理器要实现AuthenticationSuccessHandler接口
 * 当用户登录成功会自动执行该处理器中的onAuthenticationSuccess方法
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        try {
            System.out.println("用户认证信息...");
            //从认证对象中获得认证主体,主体是User对象
            User user = (User) authentication.getPrincipal();
            //将存放有用户信息的json串解析为UserInfo对象
            UserInfo userInfo = JSONObject.parseObject(user.getUsername(), UserInfo.class);
            //将userInfo中需要使用的数据转存到CurUser对象中
            CurUser curUser = new CurUser();
            curUser.setUser_id(userInfo.getUser_id());
            curUser.setUser_name(userInfo.getUser_name());

            //System.out.println("---");
            // System.out.println(SecurityContextHolder.getContext().getAuthentication());
            request.getSession().setAttribute("curUser",curUser);

            //清空SecurityContextHolder中的认证信息，清楚后Security认为当前用户未认证
            SecurityContext context=SecurityContextHolder.createEmptyContext();
            SecurityContextHolder.setContext(context);
            /**登陆成功后签发token***/
            //私钥
            PrivateKey privateKey = RsaUtils.getPrivateKey("D:\\IDEA\\workspences\\myshopping\\myshopping-project-server\\src\\main\\resources\\keys\\key.pri");


            String token = JWTUtils.generateToken(curUser, privateKey, 60 * 60 * 24 * 7);
            Map<String,Object> map=new HashMap<>();
            map.put("token",token);
            map.put("curUser",curUser);


            //将curUser对象封装到Result对象中
            Result result = Result.success("登录成功", map);

            //设置响应类型及编码集
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSONObject.toJSON(result));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
