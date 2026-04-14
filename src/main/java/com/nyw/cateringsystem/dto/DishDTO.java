package com.nyw.cateringsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className DishDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishDTO {
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
    private int salesCount;
}
