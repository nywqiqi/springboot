package com.nyw.cateringsystem.service;

import com.github.pagehelper.PageInfo;
import com.nyw.cateringsystem.bean.User;
import com.nyw.cateringsystem.dto.UserDTO;
import com.nyw.cateringsystem.dto.UserDTOForCreat;
import com.nyw.cateringsystem.dto.UserDTOForLogin;

import java.util.List;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className UserService
 */
public interface UserService {

    /**
     * 根据员工号或者员工用户名查询用户信息
     **/
    UserDTO findByStaffIdOrUsername(String staffId, String username);

    /**
     * 新增用户信息
     **/
    Long insert(UserDTOForCreat userDTO);

    /**
     * 根据id查询用户信息
     **/
    UserDTO findById(Long id);

    /**
     * 根据id删除用户信息
     **/
    boolean deleteById(List<Long> ids);

    /**
     * 根据员工号或用户名修改用户密码
     **/
    boolean updatePassword(String staffId,String username,String password);

    /**
     * 更新用户信息
     **/
    boolean updatePartOfUser(UserDTO userDTO);

    /**
     * 查询所有用户信息
     **/
    PageInfo<UserDTO> findAll(int page, int pageSize);

    /**
     * 根据角色查询用户信息
     **/
    PageInfo<UserDTO> findByRole(String role, int page, int pageSize);

    UserDTOForLogin startLogin(UserDTOForLogin userDTOForLogin);
}
