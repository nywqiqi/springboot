package com.nyw.cateringsystem.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public enum RoleEnum {
    ADMIN("管理员"),
    STAFF("职员"),
    CUSTOMER("顾客");

    @Getter
    @Setter
    private String value;
}
