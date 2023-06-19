package com.shopping.commons;


import com.shopping.model.Orders;
import lombok.Data;

@Data
public class OrderParam {
    private Orders order;
    private Integer book_ids[];
}
