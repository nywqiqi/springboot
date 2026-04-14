package com.nyw.cateringsystem.converter;

import com.nyw.cateringsystem.bean.User;
import com.nyw.cateringsystem.dto.UserDTO;
import com.nyw.cateringsystem.dto.UserDTOForCreat;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className UserConverter
 */
public class UserConverter {

    public static UserDTO convertToUserDTO(User user) {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setStaffId(user.getStaffId());
//        userDTO.setRole(user.getRole());
//        userDTO.setName(user.getName());
//        userDTO.setPhone(user.getPhone());
//        userDTO.setEmail(user.getEmail());
        return UserDTO.builder()
                .id(user.getId())
                .staffId(user.getStaffId())
                .role(user.getRole())
                .name(user.getName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
    }

    public static User convertToUser(UserDTOForCreat userDTO) {
//        User user = new User();
//        user.setId(userDTO.getId());
//        user.setStaffId(userDTO.getStaffId());
//        user.setUsername(userDTO.getUsername());
//        user.setPassword(userDTO.getPassword());
//        user.setRole(userDTO.getRole());
//        user.setName(userDTO.getName());
//        user.setPhone(userDTO.getPhone());
//        user.setEmail(userDTO.getEmail());
        return User.builder()
                .id(userDTO.getId())
                .staffId(userDTO.getStaffId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .name(userDTO.getName())
                .phone(userDTO.getPhone())
                .email(userDTO.getEmail())
                .build();
    }

    public static User convertToUser(UserDTO userDTO) {
//        User user = new User();
//        user.setId(userDTO.getId());
//        user.setStaffId(userDTO.getStaffId());
//        user.setRole(userDTO.getRole());
//        user.setName(userDTO.getName());
//        user.setPhone(userDTO.getPhone());
//        user.setEmail(userDTO.getEmail());
        return User.builder()
                .id(userDTO.getId())
                .staffId(userDTO.getStaffId())
                .role(userDTO.getRole())
                .name(userDTO.getName())
                .phone(userDTO.getPhone())
                .email(userDTO.getEmail())
                .build();
    }
}
