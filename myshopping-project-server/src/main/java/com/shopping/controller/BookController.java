package com.shopping.controller;

import com.shopping.commons.PageParam;
import com.shopping.model.Book;
import com.shopping.model.view.CommentView;
import com.shopping.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;
    @GetMapping("/getBooks")
    public List<Book> getBooks(){
        return bookService.getBooks(0);
    }

    @GetMapping("/queryBookById")
    public Book queryBookById(int book_id){
        return bookService.queryBookById(book_id);
    }

    @GetMapping("/queryCommentByBookId")
    public Map<String,Object> queryCommentByBookId(int book_id, PageParam pageParam){

        return bookService.queryCommentByBookId(book_id,pageParam);
    }
}
