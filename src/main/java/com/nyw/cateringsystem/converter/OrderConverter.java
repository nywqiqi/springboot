package com.nyw.cateringsystem.converter;

import com.nyw.cateringsystem.bean.Order;
import com.nyw.cateringsystem.dto.OrderDTO;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className OrderConverter
 */
public class OrderConverter {

    public static OrderDTO convertToOrderDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .tableId(order.getTableId())
                .createTime(order.getCreateTime())
                .orderStatus(order.getOrderStatus())
                .totalAmount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .payTime(order.getPayTime())
                .actualAmount(order.getActualAmount())
                .build();
    }

    public static Order convertToOrder(OrderDTO orderDTO) {
        return Order.builder()
                .id(orderDTO.getId())
                .orderNumber(orderDTO.getOrderNumber())
                .tableId(orderDTO.getTableId())
                .createTime(orderDTO.getCreateTime())
                .orderStatus(orderDTO.getOrderStatus())
                .totalAmount(orderDTO.getTotalAmount())
                .paymentMethod(orderDTO.getPaymentMethod())
                .payTime(orderDTO.getPayTime())
                .actualAmount(orderDTO.getActualAmount())
                .build();
    }
}
