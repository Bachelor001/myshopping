package com.shopping.mapper;

import com.shopping.model.Orders;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    @Insert("insert into tbl_order values(#{order_id},#{user_id},#{order_to_name},#{order_to_address},#{order_to_phone},now(),#{order_status},#{order_total_price})")
    void  addOrder(Orders orders);
}
