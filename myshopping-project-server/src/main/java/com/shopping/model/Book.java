package com.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {
    private Integer book_id;
    private String book_name;
    private Double book_price;
    private String book_image;
    private String book_desc;
    private Integer book_is_display;


}
