package com.nyw.cateringsystem.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜品信息表
 * t_dish
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dish implements Serializable {
    private Long id;

    /**
     * 菜品名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 描述
     */
    private String description;

    /**
     * 分类（如：川菜、甜点）
     */
    private String category;

    /**
     * 是否上架（true=上架）
     */
    private Boolean available;

    /**
     * 菜品图片URL
     */
    private String imageUrl;

    /**
     * 菜品月销售量
     */
    private Integer salesCount;

    private static final long serialVersionUID = 1L;
}