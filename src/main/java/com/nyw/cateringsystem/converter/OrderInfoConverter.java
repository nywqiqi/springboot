package com.nyw.cateringsystem.converter;

import com.nyw.cateringsystem.bean.OrderInfo;
import com.nyw.cateringsystem.dto.OrderInfoDTO;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className OrderInfoConverter
 */
public class OrderInfoConverter {

    public static OrderInfoDTO convertToOrderInfoDto(OrderInfo orderInfo) {
        return OrderInfoDTO.builder()
                .dishName(orderInfo.getDishName())
                .price(orderInfo.getPrice())
                .quantity(orderInfo.getQuantity())
                .subtotal(orderInfo.getSubtotal())
                .build();
    }

    public static OrderInfo convertToOrderInfo(OrderInfoDTO orderInfoDTO) {
        return OrderInfo.builder()
                .dishName(orderInfoDTO.getDishName())
                .price(orderInfoDTO.getPrice())
                .quantity(orderInfoDTO.getQuantity())
                .subtotal(orderInfoDTO.getSubtotal())
                .build();
    }
}
