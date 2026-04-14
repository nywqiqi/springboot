package com.nyw.cateringsystem.dto;

import com.nyw.cateringsystem.consts.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className UserDTOForLogin
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTOForLogin {

    private String username;

    private String password;

    private String role = RoleEnum.CUSTOMER.name();

    private boolean isLogin = false;

}
