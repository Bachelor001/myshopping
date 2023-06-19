package com.shopping.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 当前登录用户对象
 * 其中存放用户登录后需要的数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurUser implements Serializable {
    private Integer user_id;
    private String user_name;
}
