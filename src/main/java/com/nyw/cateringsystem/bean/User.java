package com.nyw.cateringsystem.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统用户表
 * t_user
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    private Long id;

    /**
     * 员工号
     */
    private String staffId;

    /**
     * 用户名（登录用）
     */
    private String username;

    /**
     * 加密密码
     */
    private String password;

    /**
     * 角色（ADMIN, STAFF,CUSTOMER）
     */
    private String role;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建用户信息时间
     */
    private Date creatTime;

    /**
     * 更新用户信息时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}