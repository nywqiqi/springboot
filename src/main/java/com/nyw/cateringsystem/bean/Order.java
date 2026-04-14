package com.nyw.cateringsystem.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单信息表（含账单）
 * t_order
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order implements Serializable {
    private Long id;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 关联桌面ID
     */
    private Long tableId;

    /**
     * 下单时间(默认值)
     */
    private Date createTime;

    /**
     * 订单状态（PENDING/COMPLETED）(默认值)
     */
    private String orderStatus;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 支付方式（现金/支付宝/微信）
     */
    private String paymentMethod;

    /**
     * 结账时间
     */
    private Date payTime;

    /**
     * 实际支付金额（可能含折扣）
     */
    private BigDecimal actualAmount;

    private static final long serialVersionUID = 1L;
}