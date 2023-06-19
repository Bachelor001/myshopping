package com.shopping.mapper;

import com.shopping.model.view.CommentView;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentMapper {

    List<CommentView> getCommentByBookId(int book_id);
}
