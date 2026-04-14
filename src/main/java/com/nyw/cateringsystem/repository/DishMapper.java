package com.nyw.cateringsystem.repository;

import com.nyw.cateringsystem.bean.Dish;
import com.nyw.cateringsystem.dto.DishDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishMapper {

    Dish selectByPrimaryKey(Long id);

    List<Dish> selectAll(boolean isCustomer);

    List<Dish> selectByName(String name);

    List<Dish> selectByCategory(String category);

    int insert(Dish dish);

    int deleteByIds(List<Long> ids);

    int updateDishById(Dish dish);

}