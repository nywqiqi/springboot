package com.nyw.cateringsystem.converter;

import com.nyw.cateringsystem.bean.Dish;
import com.nyw.cateringsystem.dto.DishDTO;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className DishConverter
 */
public class DishConverter {

    public static DishDTO convertToDishDTO(Dish dish) {
//        DishDTO dishDTO = new DishDTO();
//        dishDTO.setId(dish.getId());
//        dishDTO.setName(dish.getName());
//        dishDTO.setPrice(dish.getPrice());
//        dishDTO.setDescription(dish.getDescription());
//        dishDTO.setCategory(dish.getCategory());
//        dishDTO.setAvailable(dish.getAvailable());
//        dishDTO.setImageUrl(dish.getImageUrl());
//        dishDTO.setSalesCount(dish.getSalesCount());
        return DishDTO.builder()
                .id(dish.getId())
                .name(dish.getName())
                .price(dish.getPrice())
                .description(dish.getDescription())
                .category(dish.getCategory())
                .available(dish.getAvailable())
                .imageUrl(dish.getImageUrl())
                .salesCount(dish.getSalesCount())
                .build();

    }

    public static Dish convertToDish(DishDTO dishDTO) {
//        Dish dish = new Dish();
//        dish.setId(dishDTO.getId());
//        dish.setName(dishDTO.getName());
//        dish.setPrice(dishDTO.getPrice());
//        dish.setDescription(dishDTO.getDescription());
//        dish.setCategory(dishDTO.getCategory());
//        dish.setAvailable(dishDTO.getAvailable());
//        dish.setImageUrl(dishDTO.getImageUrl());
//        dish.setSalesCount(dishDTO.getSalesCount());
        return Dish.builder()
                .id(dishDTO.getId())
                .name(dishDTO.getName())
                .price(dishDTO.getPrice())
                .description(dishDTO.getDescription())
                .category(dishDTO.getCategory())
                .available(dishDTO.getAvailable())
                .imageUrl(dishDTO.getImageUrl())
                .salesCount(dishDTO.getSalesCount())
                .build();
    }
}
