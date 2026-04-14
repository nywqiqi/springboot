package com.nyw.cateringsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className TableInfoDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableInfoDTO {
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
}
