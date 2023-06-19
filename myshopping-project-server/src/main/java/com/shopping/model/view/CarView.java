package com.shopping.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarView implements Serializable {
    private Integer book_id;
    private String book_name;
    private Double book_price;
    private String book_image;
    private Integer car_count;
    private Double total;
}
