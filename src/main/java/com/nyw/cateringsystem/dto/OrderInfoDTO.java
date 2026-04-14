package com.nyw.cateringsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className OrderInfoDTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderInfoDTO {

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
}
