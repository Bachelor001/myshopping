package com.shopping.service;

import com.shopping.model.OrderDesc;
import com.shopping.model.Orders;

import java.util.List;

public interface OrderService {
    public  void addOrder(Orders orders,List<OrderDesc> orderDescs);

}
