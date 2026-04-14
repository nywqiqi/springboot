package com.nyw.cateringsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className OrderDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
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
}
