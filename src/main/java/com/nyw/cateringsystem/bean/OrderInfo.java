package com.nyw.cateringsystem.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * t_order_info
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInfo implements Serializable {
    private Long id;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 菜品ID
     */
    private Long dishId;

    /**
     * 菜品名称（下单时的快照）
     */
    private String dishName;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 小计
     */
    private BigDecimal subtotal;

    private static final long serialVersionUID = 1L;
}