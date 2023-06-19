package com.shopping.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页参数类,用于封装分页参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParam {
    private Integer pageSize;//每页显示的记录数(行数)
    private Integer pageNum;//当前页的页码
}
