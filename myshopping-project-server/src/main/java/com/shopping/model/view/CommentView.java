package com.shopping.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Comment的试图类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentView {
    private Integer comment_id;
    private String comment_message;
    private Date comment_time;
    private String user_name;
}
