package com.nyw.cateringsystem.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className CodeEnum
 */
@AllArgsConstructor
@NoArgsConstructor
public enum StatusEnum {
    PENDING("待处理"),
    COMPLETED("已处理"),
    FREE("空闲中"),
    OCCUPIED("占用中"),
    RESERVED("已预定");

    @Getter
    @Setter
    String status;
}
