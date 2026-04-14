package com.nyw.cateringsystem.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 桌面信息表
 * t_table_info
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableInfo implements Serializable {
    private Long id;

    /**
     * 桌号（如：A01, 101）
     */
    private String tableNumber;

    /**
     * 状态（FREE/OCCUPIED/RESERVED）
     */
    private String tableStatus;

    /**
     * 容纳人数
     */
    private Integer capacity;

    private static final long serialVersionUID = 1L;
}