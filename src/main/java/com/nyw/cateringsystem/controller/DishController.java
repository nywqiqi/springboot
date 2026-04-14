package com.nyw.cateringsystem.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nyw.cateringsystem.dto.DishDTO;
import com.nyw.cateringsystem.result.R;
import com.nyw.cateringsystem.service.DishService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className DishController
 */
@RestController
public class DishController {

    @Resource
    private DishService dishService;

    @GetMapping("/dishes/{id}")
    public R<DishDTO> getDishById(@PathVariable Long id) {
        return R.ok(dishService.findById(id));
    }

    @GetMapping("/dishes")
    public R<PageInfo<DishDTO>> getDishByPageNumber(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "4") int pageSize,
                                           @RequestParam(defaultValue = "true") boolean isCustomer) {
        return R.ok(dishService.findByPage(pageNum, pageSize, isCustomer));
    }

    @GetMapping("/dishes/name")
    public R<PageInfo<DishDTO>> getByName(@RequestParam String name) {
        return R.ok(dishService.findByName(name));
    }

    @GetMapping("/dishes/category")
    public R<PageInfo<DishDTO>> getDishByCategory(@RequestParam String category) {
        return R.ok(dishService.findByCategory(category));
    }

    @PostMapping("/dishes")
    public R<Long> saveDish(@RequestBody DishDTO dishDTO) {
        //System.out.println(dishDTO.getSalesCount());
        return R.ok(dishService.save(dishDTO));
    }

    @DeleteMapping("/dishes")
    public R<Boolean> deleteDish(@RequestParam(defaultValue = "") List<Long> id) {
        return R.ok(dishService.deleteByIds(id));
    }

    @PutMapping("/dishes")
    public R<Boolean> updateDish(@RequestBody DishDTO dishDTO) {
        return R.ok(dishService.updateDishById(dishDTO));
    }
}
