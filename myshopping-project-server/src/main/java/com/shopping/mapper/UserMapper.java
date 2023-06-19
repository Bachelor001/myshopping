package com.shopping.mapper;

import com.shopping.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    /**
     * 根据用户名获得用户信息
     * @param username
     * @return
     */
    @Select("select * from tbl_user where user_name=#{username}")
    public UserInfo getUserByName(String username);

    @Insert("insert into tbl_user values(default,#{user_name},#{user_password},#{user_email},#{user_birthday},#{user_hobbys},#{user_sex},#{user_address},0)")
    void addUser(UserInfo userInfo);
}
