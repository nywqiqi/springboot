package com.nyw.cateringsystem.service;

import com.github.pagehelper.PageInfo;
import com.nyw.cateringsystem.dto.OrderInfoDTO;

import java.util.List;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className OrderInfoService
 */
public interface OrderInfoService {

    /**
     * 查询所有订单详细菜单
     **/
    PageInfo<OrderInfoDTO> findAll(int pageNum, int pageSize);

    /**
     * 根据桌号查询所有订单详细菜单
     **/
    PageInfo<OrderInfoDTO> findByTableNum(int pageNum, int pageSize, String tableNum);

    /**
     * 新增菜品订单明细(点菜)
     **/
    Integer batchInsertOrderInfo(String tableNum, List<OrderInfoDTO> orderInfoDTOs);

    /**
     * 根据id更新菜品订单明细
     **/
    boolean updateOrderInfo(Long id, OrderInfoDTO orderInfoDTO);

    /**
     * 根据id删除菜品订单明细
     **/
    boolean deleteOrderInfo(List<Long> id);
}
