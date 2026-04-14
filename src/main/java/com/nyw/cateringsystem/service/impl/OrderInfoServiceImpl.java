package com.nyw.cateringsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nyw.cateringsystem.bean.Dish;
import com.nyw.cateringsystem.bean.Order;
import com.nyw.cateringsystem.bean.OrderInfo;
import com.nyw.cateringsystem.bean.TableInfo;
import com.nyw.cateringsystem.consts.StatusEnum;
import com.nyw.cateringsystem.converter.OrderInfoConverter;
import com.nyw.cateringsystem.dto.OrderDTO;
import com.nyw.cateringsystem.dto.OrderInfoDTO;
import com.nyw.cateringsystem.repository.DishMapper;
import com.nyw.cateringsystem.repository.OrderInfoMapper;
import com.nyw.cateringsystem.repository.OrderMapper;
import com.nyw.cateringsystem.repository.TableInfoMapper;
import com.nyw.cateringsystem.service.OrderInfoService;
import com.nyw.cateringsystem.service.OrderService;
import com.nyw.cateringsystem.util.OrderUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className OrderInfoServiceImpl
 */
@Service
@Transactional
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private OrderService orderService;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private TableInfoMapper tableInfoMapper;
    @Resource
    private DishMapper dishMapper;

    public boolean checkInputForInsert(OrderInfoDTO orderInfoDTO) {
        if (orderInfoDTO.getDishName() == null || orderInfoDTO.getDishName().isEmpty()) {
            throw new IllegalArgumentException("菜品名为空或输入有误");
        }
//        if(orderInfoDTO.getPrice() != null && orderInfoDTO.getPrice().compareTo(BigDecimal.ZERO) < 0 ) {
//            throw new IllegalArgumentException("菜品价格输入有误")
//        }
        if (orderInfoDTO.getQuantity() == null || orderInfoDTO.getQuantity() <= 0) {
            throw new IllegalArgumentException("菜品数量为空或输入有误");
        }
        return true;
    }

    @Override
    public PageInfo<OrderInfoDTO> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderInfo> orderInfos = orderInfoMapper.selectAll();
        List<OrderInfoDTO> orderInfoDTOS = new ArrayList<>();
        orderInfos.forEach(orderInfo -> {
            orderInfoDTOS.add(OrderInfoConverter.convertToOrderInfoDto(orderInfo));
        });
        return new PageInfo<>(orderInfoDTOS);
    }

    @Override
    public PageInfo<OrderInfoDTO> findByTableNum(int pageNum, int pageSize, String tableNum) {
        if(tableNum == null || tableNum.isEmpty()) {
            throw new IllegalArgumentException("桌号为空或输入有误");
        }
        if (!tableNum.matches("^[a-zA-Z][0-9]{2}$")) {
            throw new IllegalArgumentException("桌号为空或输入有误");
        }
        List<TableInfo> tableInfos = tableInfoMapper.selectByTableNum(tableNum);
        if (tableInfos.isEmpty()) {
            throw new IllegalArgumentException("桌号为空或输入有误");
        }
        Long eligibleTableId = 0L;
        for (TableInfo tableInfo : tableInfos) {
            if(tableInfo.getTableNumber().equals(tableNum.toUpperCase())) {
                eligibleTableId = tableInfo.getId();
            }
        }
        List<Order> orders = orderMapper.selectByTableId(eligibleTableId);
        Long orderId = 0L;
        if(!orders.isEmpty()) {
            //查询当前桌面未支付的订单
            for (Order orderTemp : orders) {
                if(orderTemp.getOrderStatus().equals(StatusEnum.PENDING.name())) {
                    orderId = orderTemp.getId();
                    break;
                }
            }
        }
        PageHelper.startPage(pageNum, pageSize);
        List<OrderInfo> orderInfos = orderInfoMapper.selectByOrderId(orderId);
        List<OrderInfoDTO> orderInfoDTOS = new ArrayList<>();
        orderInfos.forEach(orderInfo -> {
            orderInfoDTOS.add(OrderInfoConverter.convertToOrderInfoDto(orderInfo));
        });
        return new PageInfo<>(orderInfoDTOS);
    }

    @Override
    public Integer batchInsertOrderInfo(String tableNum, List<OrderInfoDTO> orderInfoDTOs) {
        if(tableNum == null || tableNum.isEmpty()) {
            throw new IllegalArgumentException("桌号为空或输入有误");
        }
        if (!tableNum.matches("^[a-zA-Z][0-9]{2}$")) {
            throw new IllegalArgumentException("桌号为空或输入有误");
        }
        List<TableInfo> tableInfos = tableInfoMapper.selectByTableNum(tableNum);
        if (tableInfos.isEmpty()) {
            throw new IllegalArgumentException("桌号为空或输入有误");
        }
        Long tableId = 0L;
        TableInfo tableInfo = new TableInfo();
        //是否新建订单
        boolean needInsertNewOrder = true;
        //获取桌面id并查询
        for (TableInfo tableInfoTemp : tableInfos) {
            if(tableInfoTemp.getTableNumber().equals(tableNum.toUpperCase())) {
                tableId = tableInfoTemp.getId();
                tableInfo = tableInfoMapper.selectById(tableId);
            }
        }
        Order order = Order.builder().build();
        Long orderId = 0L;
        //查询桌面所有订单
        List<Order> orders = orderMapper.selectByTableId(tableId);
        if(!orders.isEmpty()) {
            //查询当前桌面有无待支付订单,有就不创建新订单,
            for (Order orderTemp : orders) {
                if(orderTemp.getOrderStatus().equals(StatusEnum.PENDING.name())) {
                    needInsertNewOrder = false;
                    orderId = orderTemp.getId();
                    break;
                }
            }
        }
        BigDecimal totalAmount = new BigDecimal("0.00");
        //存在订单就取出订单信息
        if(orderId != 0L) {
            order = orderMapper.selectById(orderId);
            totalAmount = order.getTotalAmount();
        }
        //不存在订单就新建,存在未支付订单自动跳过新建
        if (needInsertNewOrder) {
            order = Order.builder()
                    .orderNumber(OrderUtils.generateOrderNum(tableNum.toUpperCase()))
                    .tableId(tableId)
                    .orderStatus(StatusEnum.PENDING.name())
                    .totalAmount(totalAmount)
                    .build();
            orderMapper.insert(order);
        }
        List<OrderInfo> orderInfosForInsert = new ArrayList<>();
        List<OrderInfo> orderInfosForUpdate = new ArrayList<>();
        for (OrderInfoDTO orderInfoDTO : orderInfoDTOs) {
            //检查菜品名和数量
            checkInputForInsert(orderInfoDTO);
            OrderInfo orderInfo = OrderInfoConverter.convertToOrderInfo(orderInfoDTO);
            //根据菜品名取出菜品集合
            List<Dish> dishes = dishMapper.selectByName(orderInfoDTO.getDishName());
            if (dishes == null || dishes.isEmpty()) {
                throw new IllegalArgumentException("菜品名为空、输入有误或菜品未上架");
            }
            //为price字段赋值
            dishes.forEach(dish -> {
                if(!dish.getAvailable()) {
                    throw new IllegalArgumentException("菜品未上架");
                }
                if (orderInfo.getDishName().equals(dish.getName())) {
                    orderInfo.setPrice(dish.getPrice());
                    orderInfo.setDishId(dish.getId());
                }
            });
            BigDecimal subtotal = orderInfo.getPrice().multiply(BigDecimal.valueOf(orderInfoDTO.getQuantity()));
            //订单小计
            orderInfo.setSubtotal(subtotal);
            orderInfo.setOrderId(order.getId());
            //更新总订单中的总价
            totalAmount = totalAmount.add(orderInfo.getSubtotal());
            //根据菜品id和总订单id取出订单
            OrderInfo findOrderInfo = orderInfoMapper.selectByOrderAndDishId(order.getId(),orderInfo.getDishId());
            if(findOrderInfo != null) {
                orderInfosForUpdate.add(OrderInfo.builder()
                        .quantity(orderInfoDTO.getQuantity() + findOrderInfo.getQuantity())
                        .id(findOrderInfo.getId())
                        .subtotal(orderInfo.getPrice().multiply(BigDecimal.valueOf(orderInfoDTO.getQuantity() + findOrderInfo.getQuantity())))
                        .build());
                continue;
            }
            orderInfosForInsert.add(orderInfo);
        }
        if(orderInfosForInsert.isEmpty()) {
            throw new IllegalArgumentException("重复点菜,如需继续请使用菜品加量功能");
        }
        //更新订单总价
        order.setTotalAmount(totalAmount);
        orderMapper.updateById(order);
        tableInfo.setTableStatus(StatusEnum.OCCUPIED.name());
        tableInfoMapper.updateByID(tableInfo);
        //执行SQL语句
        return orderInfoMapper.batchInsert(orderInfosForInsert) + orderInfoMapper.batchUpdate(orderInfosForUpdate);
    }

    @Override
    public boolean updateOrderInfo(Long id, OrderInfoDTO orderInfoDTO) {
        return false;
    }

    @Override
    public boolean deleteOrderInfo(List<Long> id) {
        return false;
    }
}
