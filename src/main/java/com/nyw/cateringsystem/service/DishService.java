package com.nyw.cateringsystem.service;

import com.github.pagehelper.PageInfo;
import com.nyw.cateringsystem.bean.Dish;
import com.nyw.cateringsystem.dto.DishDTO;

import java.util.List;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className DishService
 */
public interface DishService {

    /**
     * 根据菜品id查询
     **/
    DishDTO findById(Long dishId);

    /**
     * 查询所有菜品
     *
     **/
    List<DishDTO> findAll(boolean isCustomer);

    /**
     * 根据页码查询
     **/
    PageInfo<DishDTO> findByPage(int pageNum, int pageSize, boolean isCustomer);

    /**
     * 根据名字模糊查询
     **/
    PageInfo<DishDTO> findByName(String name);

    /**
     * 根据类别查询
     **/
    PageInfo<DishDTO> findByCategory(String category);

    /**
     * 新增菜品（管理员权限）
     **/
    Long save(DishDTO dishDTO);

    /**
     * 根据id删除菜品（管理员权限）
     **/
    boolean deleteByIds(List<Long> ids);

    /**
     * 根据id更新菜品（管理员权限）
     **/
    boolean updateDishById(DishDTO dishDTO);

}
