package com.nyw.cateringsystem.repository;

import com.nyw.cateringsystem.bean.OrderInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderInfoMapper {
    int batchDelete(List<Long> ids);

    int batchInsert(@Param("orderInfos") List<OrderInfo> orderInfos);

    OrderInfo selectById(Long id);

    List<OrderInfo> selectAll();

    OrderInfo selectByOrderAndDishId(@Param("orderId") Long orderId,@Param("dishId") Long dishId);

    List<OrderInfo> selectByOrderId(Long orderId);

    List<OrderInfo> selectByDishId(Long dishId);

    int updateById(OrderInfo orderInfo);

    int batchUpdate(@Param("orderInfos") List<OrderInfo> orderInfos);
}