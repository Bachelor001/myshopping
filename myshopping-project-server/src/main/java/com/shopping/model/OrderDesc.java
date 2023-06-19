package com.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDesc {
    private  Integer desc_id;
    private  String order_id;
    private  Integer book_id;
    private  Integer  count;
}
