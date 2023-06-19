package com.shopping.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.commons.PageParam;
import com.shopping.mapper.BookMapper;
import com.shopping.mapper.CommentMapper;
import com.shopping.model.Book;
import com.shopping.model.view.CommentView;
import com.shopping.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    private BookMapper bookMapper;
    private CommentMapper commentMapper;
    private RedisTemplate redisTemplate;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper, CommentMapper commentMapper, RedisTemplate redisTemplate) {
        this.bookMapper = bookMapper;
        this.commentMapper = commentMapper;
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Book>(Book.class));
    }

    @Override
    public List<Book> getBooks(Integer... isDisplay) {

        /**
         * 引入redis存储商品信息，实现思路:
         *  1.检测查看的商品是否为上架商品(isDisplay的值是否为0)
         *      1.1 如果isDisplay的值为0则表示获得所有上架商品，默认从redis中读取
         *      1.2 如果isDisplay的值不为0则从数据库中读取数据
         *  2.检测redis中是否包含已上架的商品
         *      2.1 如果包含直接从redis中获取数据
         *      2.2 如果不包含，从数据库中获取已上架数据
         *  3.从数据库中获取已上架数据，并向redis中存储一份，然后返回
          */

        /*
        * 版本1
        * */
            //1.检测查看的商品是否为上架商品(isDisplay的值是否为0)
            if(isDisplay[0]==0){
                //获得操作RedisHash的对象
                HashOperations hashOperations = redisTemplate.opsForHash();
                //2.检测redis中是否包含已上架的商品
                Map<String,Book> bookListMap = hashOperations.entries("bookList");

                if(bookListMap.size()!=0){//从redis中获取数据
                    //从redis中获取商品列表
                    Collection<Book> values = bookListMap.values();
                    List<Book> bookList = new ArrayList<>();
                    for (Book book : values){
                        bookList.add(book);
                    }
                    return bookList;
                }

                //从数据库中获取商品列表
                List<Book> bookList = bookMapper.getBooks(isDisplay);

                Map<String,Object> booksMap = new HashMap<>();
                //遍历bookList将bookList中的数据转存到booksMap集合中
                for(Book book : bookList){
                    booksMap.put("BOOKKEY:"+book.getBook_id(), book);
                }

                //将booksMap添加到redis中，redis的key为bookList
                hashOperations.putAll("bookList",booksMap);
                return bookList;
            }
            //从数据库中获取商品列表
            return bookMapper.getBooks(isDisplay);

    }

    @Override
    public Book queryBookById(int book_id) {
        return bookMapper.getBookById(book_id).get(0);
    }

    @Override
    public Map<String,Object> queryCommentByBookId(int book_id, PageParam pageParam) {
        //使用PageHelper实现分页,实现分页的三段代码不能分割
        //开启分页插件
        PageHelper.startPage(pageParam);
        //查询数据
        List<CommentView> commentViews = commentMapper.getCommentByBookId(book_id);
        //将查询结果封装到PageInfo对象中,该对象是PageHelper提供
        //pageInfo对象会根据commentViews参数封装分页相关数据
        PageInfo pageInfo = new PageInfo(commentViews);

        //将分页所需要返回到客户端的数据封装到Map集合中
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("commentList",pageInfo.getList());


        return map;
    }
}
