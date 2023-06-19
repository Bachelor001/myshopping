package com.shopping.service.impl;

import com.shopping.mapper.OrderDescMapper;
import com.shopping.mapper.OrderMapper;
import com.shopping.model.Car;
import com.shopping.model.OrderDesc;
import com.shopping.model.Orders;
import com.shopping.service.CarService;
import com.shopping.service.OrderService;
import com.shopping.utils.GenarateOrderId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CarService carService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDescMapper orderDescMapper;

    @Override
    public void addOrder(Orders orders, List<OrderDesc> orderDescs) {
        /**
         * 实现思路：添加订单-向订单表和订单明细表中添加数据
         * 1.补全订单及订单明细中的相关属性
         * 2.向数据库中添加订单和点歌单明细
         * 3.从redis中将已买的商品删除
         */
        orders.setOrder_id(GenarateOrderId.getOrderId());

        double total = 0;
        //遍历orderDescs集合并添加相关属性数据
        for (OrderDesc desc : orderDescs) {
            desc.setOrder_id(orders.getOrder_id());
            //从redis中读取购物车数据
            Car car = carService.getCarInfoById(orders.getUser_id(), desc.getBook_id());
            //设置购买数量
            desc.setCount(car.getCar_count());
            total = total + car.getBook_price() * car.getCar_count();
        }
        orders.setOrder_total_price(total);

        orderMapper.addOrder(orders);
        orderDescMapper.addOrderDesc(orderDescs);
    }
}
