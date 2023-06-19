package com.shopping.service;

import com.shopping.commons.PageParam;
import com.shopping.model.Book;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BookService {
    /**
     * 获得所以图书
     * @param display 是否上架 0表示上架  -1表示下架 如果没有参数表示所有商品(含上架和下架)
     * @return
     */
    List<Book> getBooks(Integer... isDisplay);

    Book queryBookById(int book_id);

    Map<String,Object> queryCommentByBookId(int book_id, PageParam pageParam);
}
