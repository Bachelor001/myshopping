package com.shopping.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private String order_id;
    private Integer user_id;
    private String order_to_name;
    private String order_to_address;
    private String order_to_phone;
    private Date order_time;
    private Integer order_status;
    private Double order_total_price;
}
