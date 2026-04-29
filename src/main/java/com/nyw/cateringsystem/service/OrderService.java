package com.nyw.cateringsystem.service;

import com.github.pagehelper.PageInfo;
import com.nyw.cateringsystem.dto.OrderDTO;

import java.util.List;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className OrderService
 */
public interface OrderService {

    /**
     * 根据页码查询
     **/
    PageInfo<OrderDTO> selectByPage(int pageNum, int pageSize);

    /**
     * 根据id或者订单号查询
     **/
    OrderDTO selectByIdOrOrderNum(Long id,String orderNum);

    /**
     * 根据桌面id查询全部订单
     **/
    PageInfo<OrderDTO> selectByTableId(int pageNum, int pageSize, Long tableId);

    /**
     * 根据桌号查询全部订单
     **/
    PageInfo<OrderDTO> selectByTableNum(int pageNum, int pageSize, String tableNum);

    /**
     * 新增订单
     **/
    Long insertOrder(OrderDTO orderDTO);

    /**
     * 根据id批量删除订单
     **/
    boolean deleteById(List<Long> ids);

    /**
     * 根据id更新订单信息
     **/
    boolean updateOrder(Long id, OrderDTO orderDTO);

    /**
     * 结账：更新支付信息并将订单状态改为已完成
     **/
    boolean checkout(Long id, OrderDTO orderDTO);
}
