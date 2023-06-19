package com.shopping.mapper;


import com.shopping.model.OrderDesc;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDescMapper {
    /**
     * 添加订单明细
     * @param orderDescList
     */
    void addOrderDesc(@Param("descList") List<OrderDesc> orderDescList);

}
