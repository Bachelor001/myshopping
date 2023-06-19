package com.shopping.service.impl;

import com.shopping.mapper.UserMapper;
import com.shopping.model.UserInfo;
import com.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void register(UserInfo userInfo) throws Exception {
        //对UserInfo中的密码进行加密
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(userInfo.getUser_password());
        userInfo.setUser_password(encodePassword);

        userMapper.addUser(userInfo);
    }
}
