package com.nyw.cateringsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nyw.cateringsystem.bean.Order;
import com.nyw.cateringsystem.bean.TableInfo;
import com.nyw.cateringsystem.consts.StatusEnum;
import com.nyw.cateringsystem.converter.OrderConverter;
import com.nyw.cateringsystem.dto.OrderDTO;
import com.nyw.cateringsystem.exception.SourceNotFoundException;
import com.nyw.cateringsystem.repository.OrderMapper;
import com.nyw.cateringsystem.repository.TableInfoMapper;
import com.nyw.cateringsystem.service.OrderService;
import com.nyw.cateringsystem.util.OrderUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className OrderServiceImpl
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private TableInfoMapper tableInfoMapper;

    public boolean checkInputForInsert(OrderDTO orderDTO) {
        //校验tableId是否符合非空和保存要求
        if (orderDTO.getTableId() == null || orderDTO.getTableId() <= 0) {
            throw new IllegalArgumentException("桌面id为空或输入有误");
        }
        if (tableInfoMapper.selectById(orderDTO.getTableId()) == null) {
            throw new IllegalArgumentException("桌面id为空或输入有误");
        }
        //校验orderStatus是否符合非空和保存要求
        if (orderDTO.getOrderStatus() != null && !orderDTO.getOrderStatus().isEmpty()) {
            String orderStatus = orderDTO.getOrderStatus().toUpperCase();
            if (!orderStatus.equals(StatusEnum.PENDING.name()) && !orderStatus.equals(StatusEnum.COMPLETED.name())) {
                throw new IllegalArgumentException("状态必须为 PENDING 或者 COMPLETED");
            }
        }
        //校验totalAmount是否符合非空和保存要求
        if (orderDTO.getTotalAmount() == null || orderDTO.getTotalAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("总金额为空或输入有误");
        }
        //校验paymentMethod是否保存要求
        if (orderDTO.getPaymentMethod() != null && !orderDTO.getPaymentMethod().isEmpty()) {
            String paymentMethod = orderDTO.getPaymentMethod();
            if (!"现金".equals(paymentMethod) && !"微信".equals(paymentMethod) && !"支付宝".equals(paymentMethod)) {
                throw new IllegalArgumentException("支付方式输入有误(必须为现金、微信和支付宝中的一种)");
            }
        }
        //校验actualAmount是否保存要求
        if (orderDTO.getActualAmount() != null && orderDTO.getActualAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("实际支付金额输入有误");
        }
        return true;
    }

    public boolean checkInputForUpdate(OrderDTO orderDTO) {
        //校验tableId是否符合保存要求
        if (orderDTO.getTableId() != null) {
            if (orderDTO.getTableId() <= 0) {
                throw new IllegalArgumentException("桌面id为空或输入有误");
            }
            if (tableInfoMapper.selectById(orderDTO.getTableId()) == null) {
                throw new IllegalArgumentException("桌面id为空或输入有误");
            }
        }
        //校验orderStatus是否符合保存要求
        if (orderDTO.getOrderStatus() != null && !orderDTO.getOrderStatus().isEmpty()) {
            String orderStatus = orderDTO.getOrderStatus().toUpperCase();
            if (!orderStatus.equals(StatusEnum.PENDING.name()) && !orderStatus.equals(StatusEnum.COMPLETED.name())) {
                throw new IllegalArgumentException("状态必须为 PENDING 或者 COMPLETED");
            }
        }
        //校验totalAmount是否符合非空和保存要求
        if (orderDTO.getTotalAmount() != null && orderDTO.getTotalAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("总金额为空或输入有误");
        }
        //校验paymentMethod是否保存要求
        if (orderDTO.getPaymentMethod() != null && !orderDTO.getPaymentMethod().isEmpty()) {
            String paymentMethod = orderDTO.getPaymentMethod();
            if (!"现金".equals(paymentMethod) && !"微信".equals(paymentMethod) && !"支付宝".equals(paymentMethod)) {
                throw new IllegalArgumentException("支付方式输入有误(必须为现金、微信和支付宝中的一种)");
            }
        }
        //校验actualAmount是否保存要求
        if (orderDTO.getActualAmount() != null && orderDTO.getActualAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("实际支付金额输入有误");
        }
        return true;
    }

    @Override
    public PageInfo<OrderDTO> selectByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orders = orderMapper.selectAll();
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(order -> {
            orderDTOS.add(OrderConverter.convertToOrderDTO(order));
        });
        return new PageInfo<>(orderDTOS);
    }

    @Override
    public OrderDTO selectByIdOrOrderNum(Long id, String orderNum) {
        if (id == null && orderNum == null) {
            throw new IllegalArgumentException("id and orderNum can not be null at the same time");
        }
        if (id != null) {
            if (id < 1) {
                throw new IllegalArgumentException("id can not be less than 1");
            }
            return OrderConverter.convertToOrderDTO(orderMapper.selectById(id));
        }
        return OrderConverter.convertToOrderDTO(orderMapper.selectByOrderNumber(orderNum));
    }

    @Override
    public PageInfo<OrderDTO> selectByTableId(int pageNum, int pageSize, Long tableId) {
        if(tableId == null || tableId <= 0) {
            throw new IllegalArgumentException("桌面id为空或输入有误");
        }
        if (tableInfoMapper.selectById(tableId) == null) {
            throw new IllegalArgumentException("桌面id为空或输入有误");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orders = orderMapper.selectByTableId(tableId);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(order -> {
            orderDTOS.add(OrderConverter.convertToOrderDTO(order));
        });
        return new PageInfo<>(orderDTOS);
    }

    @Override
    public PageInfo<OrderDTO> selectByTableNum(int pageNum, int pageSize, String tableNum) {
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
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orders = orderMapper.selectByTableId(eligibleTableId);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(order -> {
            orderDTOS.add(OrderConverter.convertToOrderDTO(order));
        });
        return new PageInfo<>(orderDTOS);
    }

    @Override
    public Long insertOrder(OrderDTO orderDTO) {
        checkInputForInsert(orderDTO);
        Order order = OrderConverter.convertToOrder(orderDTO);
        //自动生成orderNumber
        String orderNumber = "";
        TableInfo tableInfo = tableInfoMapper.selectById(order.getTableId());
        String tableNumber = tableInfo.getTableNumber();
        do {
            orderNumber = OrderUtils.generateOrderNum(tableNumber);
            if (orderMapper.selectByOrderNumber(orderNumber) == null) {
                order.setOrderNumber(orderNumber);
                break;
            }
        } while (true);

        if(order.getOrderStatus() == null || order.getOrderStatus().isEmpty()) {
            order.setOrderStatus(StatusEnum.PENDING.name());
        }
        if (order.getOrderStatus() != null && !order.getOrderStatus().isEmpty()) {
            order.setOrderStatus(order.getOrderStatus().toUpperCase());
        }

        orderMapper.insert(order);
        return order.getId();
    }

    @Override
    public boolean deleteById(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("删除id不能为空");
        }
        ids.forEach(id -> {
            if (orderMapper.selectById(id) == null) {
                throw new SourceNotFoundException("订单不存在");
            }
        });
        return orderMapper.deleteByIds(ids) > 0;
    }

    @Override
    public boolean updateOrder(Long id, OrderDTO orderDTO) {
        Order order = OrderConverter.convertToOrder(orderDTO);
        Order orderTemp = orderMapper.selectById(id);
        order.setId(id);
        checkInputForUpdate(orderDTO);
        //自动赋值orderStatus
        if (order.getOrderStatus() != null && !order.getOrderStatus().isEmpty()) {
            order.setOrderStatus(order.getOrderStatus().toUpperCase());
        }

        TableInfo tableInfo = tableInfoMapper.selectById(order.getTableId());

        //桌号更换时自动生成新订单号
        if (orderDTO.getTableId() != null && !orderTemp.getTableId().equals(orderDTO.getTableId())) {
            order.setOrderNumber(OrderUtils.generateOrderNum(tableInfo.getTableNumber()));
        }

        return orderMapper.updateById(order) > 0;
    }

    @Override
    public boolean checkout(Long id, OrderDTO orderDTO) {
        Order order = orderMapper.selectById(id);
        if (order == null) {
            throw new SourceNotFoundException("订单不存在");
        }
        if (orderDTO.getActualAmount() == null || orderDTO.getActualAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("实际支付金额不能为空或小于等于0");
        }
        if (orderDTO.getPaymentMethod() == null || orderDTO.getPaymentMethod().isEmpty()) {
            throw new IllegalArgumentException("支付方式不能为空");
        }
        String paymentMethod = orderDTO.getPaymentMethod();
        if (!"现金".equals(paymentMethod) && !"微信".equals(paymentMethod) && !"支付宝".equals(paymentMethod)) {
            throw new IllegalArgumentException("支付方式输入有误(必须为现金、微信和支付宝中的一种)");
        }

        order.setActualAmount(orderDTO.getActualAmount());
        order.setPaymentMethod(paymentMethod);
        order.setOrderStatus(StatusEnum.COMPLETED.name());
        order.setPayTime(new Date());

        return orderMapper.updateById(order) > 0;
    }
}
