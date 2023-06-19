package com.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 该类与tbl_user表对应
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {
    private Integer user_id;
    private String user_name;
    private String user_password;
    private String user_email;
    private Date user_birthday;
    private String user_hobbys;
    private Integer user_sex;
    private String user_address;
    private Integer user_status;

}
