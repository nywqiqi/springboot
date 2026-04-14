package com.nyw.cateringsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className UserDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;

    private String staffId;

    private String role;

    private String name;

    private String phone;

    private String email;
}
