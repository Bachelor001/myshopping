package com.shopping.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shopping.mapper.UserMapper;
import com.shopping.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDetailsService实现类
 *  该类中的loadUserByUsername方法用于获取用户的认证信息，并将这些认证信息提交给SpringSecurity进行认证
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名获得用户信息
        UserInfo userInfo = userMapper.getUserByName(username);
        //判断用户名是否正确
        if(userInfo==null){
            System.out.println("用户名不存在...");
            throw new UsernameNotFoundException("用户名不存在");
        }
        if(userInfo.getUser_status()==-1){//用户被冻结
            System.out.println("用户被冻结....");

        }


        //设置当前账户的权限集合
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        String password = userInfo.getUser_password();
        userInfo.setUser_password(null);
        //将用户的认证信息封装到User对象
        User user = new User(
                            //将用户对象转换为json串
                            JSONObject.toJSONString(userInfo),//用户名(数据载体，用户登录成功后需要使用的数据)
                            password,//密码
                            true,//账户是否启用
                            true,//账户是否过期
                            true,//凭证是否过期
                            userInfo.getUser_status()==0?true:false,//账户是否锁定
                            authorities//权限集合
                        );

        return user;//将user对象提交给SpringSecurity，由SpringSecurity进行认证
    }
}
