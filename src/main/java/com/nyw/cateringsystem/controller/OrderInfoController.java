package com.nyw.cateringsystem.controller;

import com.github.pagehelper.PageInfo;
import com.nyw.cateringsystem.dto.OrderInfoDTO;
import com.nyw.cateringsystem.result.R;
import com.nyw.cateringsystem.service.OrderInfoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className OrderInfoController
 */
@RestController
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    @GetMapping("/orderInfos")
    public R<PageInfo<OrderInfoDTO>> getOrderInfoByTableNum(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, @RequestParam String tableNum) {
        return R.ok(orderInfoService.findByTableNum(pageNum,pageSize,tableNum));
    }

    @PostMapping("/orderInfos/{tableNumber}")
    public R<Integer> saveOrderInfos(@PathVariable String tableNumber, @RequestBody List<OrderInfoDTO> orderInfoDTOs) {
        return R.ok(orderInfoService.batchInsertOrderInfo(tableNumber,orderInfoDTOs));
    }
}
