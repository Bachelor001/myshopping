package com.shopping.mapper;

import com.shopping.model.Book;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    List<Book> getBooks(Integer... isDisplay);


    List<Book> getBookById(Integer... book_ids);
}
