package com.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Car类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Serializable {
    private Integer book_id;
    private Integer user_id;
    private String book_name;
    private Double book_price;
    private String book_image;
    private Integer car_count;
    private Double total;




}
