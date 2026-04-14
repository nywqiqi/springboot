package com.nyw.cateringsystem.repository;

import com.nyw.cateringsystem.bean.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    int deleteByIds(List<Long> ids);

    int insert(Order order);

    List<Order> selectAll();

    Order selectById(Long id);

    Order selectByOrderNumber(String orderNumber);

    List<Order> selectByTableId(Long tableId);

    int updateById(Order order);
}