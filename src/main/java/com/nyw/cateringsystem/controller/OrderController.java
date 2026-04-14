package com.nyw.cateringsystem.controller;

import com.github.pagehelper.PageInfo;
import com.nyw.cateringsystem.dto.OrderDTO;
import com.nyw.cateringsystem.result.R;
import com.nyw.cateringsystem.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className OrderController
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/orders")
    public R<PageInfo<OrderDTO>> getAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int PageSize) {
        return R.ok(orderService.selectByPage(pageNum, PageSize));
    }

    @GetMapping("/orders/{id}")
    public R<OrderDTO> getByIdOrOrderNum(@PathVariable(required = false) Long id, @RequestParam(required = false) String orderNum) {
        return R.ok(orderService.selectByIdOrOrderNum(id, orderNum));
    }

    @GetMapping("/orders/table/{tableId}")
    public R<PageInfo<OrderDTO>> getByTableId(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int PageSize,
                                              @PathVariable Long tableId) {
        return R.ok(orderService.selectByTableId(pageNum, PageSize, tableId));
    }

    @GetMapping("/orders/table")
    public R<PageInfo<OrderDTO>> getByTableNum(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int PageSize,
                                              @RequestParam String tableNum) {
        return R.ok(orderService.selectByTableNum(pageNum, PageSize, tableNum));
    }

    @PostMapping("/orders")
    public R<Long> save(@RequestBody OrderDTO orderDTO) {
        return R.ok(orderService.insertOrder(orderDTO));
    }

    @DeleteMapping("/orders")
    public R<Boolean> delete(@RequestParam List<Long> id) {
        return R.ok(orderService.deleteById(id));
    }

    @PutMapping("/orders/{id}")
    public R<Boolean> update(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        return R.ok(orderService.updateOrder(id, orderDTO));
    }
}
