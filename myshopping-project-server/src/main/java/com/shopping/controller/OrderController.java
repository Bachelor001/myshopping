package com.shopping.controller;

import com.shopping.commons.CurUser;
import com.shopping.commons.OrderParam;
import com.shopping.commons.Result;
import com.shopping.model.OrderDesc;
import com.shopping.model.Orders;
import com.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/addOrder")
    public Result addOrder(@RequestBody OrderParam orderParam, HttpSession session) {
        CurUser curUser = (CurUser) session.getAttribute("curUser");
        Orders order = orderParam.getOrder();
        //设置订单的用户编号
        order.setUser_id(curUser.getUser_id());

        /**
         * 遍历book_ids数组，将要购买的商品编号封装到订单明细对象中
         * 并将该明细添加到订单明细List集合中
         */
        List<OrderDesc> orderDescList = new ArrayList<>();
        for (int book_id : orderParam.getBook_ids()) {
            OrderDesc orderDesc = new OrderDesc();
            orderDesc.setBook_id(book_id);
            orderDescList.add(orderDesc);
        }
        orderService.addOrder(order, orderDescList);
        return Result.success("添加成功");
    }

}
