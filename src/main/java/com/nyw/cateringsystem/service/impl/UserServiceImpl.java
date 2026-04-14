package com.nyw.cateringsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nyw.cateringsystem.bean.User;
import com.nyw.cateringsystem.consts.RoleEnum;
import com.nyw.cateringsystem.converter.UserConverter;
import com.nyw.cateringsystem.dto.UserDTO;
import com.nyw.cateringsystem.dto.UserDTOForCreat;
import com.nyw.cateringsystem.dto.UserDTOForLogin;
import com.nyw.cateringsystem.exception.AlreadyExistException;
import com.nyw.cateringsystem.exception.SourceNotFoundException;
import com.nyw.cateringsystem.repository.UserMapper;
import com.nyw.cateringsystem.service.UserService;
import com.nyw.cateringsystem.util.EnumUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className UserServiceImpl
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO findByStaffIdOrUsername(String staffId, String username) {
        User user = userMapper.selectByStaffIdOrUsername(staffId, username);
        if (user == null) {
            throw new SourceNotFoundException();
        }
        return UserConverter.convertToUserDTO(user);
    }

    @Override
    public Long insert(UserDTOForCreat userDTO) {
        if (!EnumUtils.isBelongTo(userDTO.getRole(), RoleEnum.class)) {//校验role输入是否有误
            throw new IllegalArgumentException("角色输入有误");
        }
        User findUser1 = userMapper.selectByStaffIdOrUsername(userDTO.getStaffId(), null);
        if (findUser1 != null) {
            throw new AlreadyExistException("资源已存在");
        }
        User findUser2 = userMapper.selectByStaffIdOrUsername(null, userDTO.getUsername());
        if (findUser2 != null) {
            throw new AlreadyExistException("资源已存在");
        }
        String role = userDTO.getRole().toUpperCase();
        userDTO.setRole(role);
        String password = userDTO.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        userDTO.setPassword(encodePassword);
        User user = UserConverter.convertToUser(userDTO);
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new SourceNotFoundException();
        }
        return UserConverter.convertToUserDTO(user);
    }

    @Override
    public boolean deleteById(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("删除id不能为空");
        }
        ids.forEach(id -> {
            if (userMapper.selectById(id) == null) {
                throw new SourceNotFoundException("用户不存在");
            }
        });
        return userMapper.deleteByIds(ids) > 0;
    }

    @Override
    public boolean updatePassword(String staffId, String username, String password) {
        User user = userMapper.selectByStaffIdOrUsername(staffId, username);
        if (user == null) {
            throw new SourceNotFoundException();
        }
        if (passwordEncoder.matches(password, user.getPassword())) {
            throw new AlreadyExistException("新密码与旧密码相同");
        }
        user.setPassword(passwordEncoder.encode(password));
        return userMapper.update(user) > 0;
    }

    @Override
    public boolean updatePartOfUser(UserDTO userDTO) {
        if (userDTO.getRole() != null) {//校验role输入是否有误
            if (!EnumUtils.isBelongTo(userDTO.getRole(), RoleEnum.class)) {
                throw new IllegalArgumentException("角色输入有误");
            }
        }
        User user = userMapper.selectById(userDTO.getId());
        if (user == null) {
            throw new SourceNotFoundException();
        }
        User userTemp = UserConverter.convertToUser(userDTO);
        userTemp.setRole(userDTO.getRole().toUpperCase());
        return userMapper.update(userTemp) > 0;
    }

    @Override
    public PageInfo<UserDTO> findAll(int page, int pageSize) {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userMapper.selectAll();
        users.forEach(user -> {
            userDTOS.add(UserConverter.convertToUserDTO(user));
        });
        PageHelper.startPage(page, pageSize);
        PageInfo<UserDTO> userPageInfo = new PageInfo<>(userDTOS);
        return userPageInfo;
    }

    @Override
    public PageInfo<UserDTO> findByRole(String role, int page, int pageSize) {
        if (!EnumUtils.isBelongTo(role, RoleEnum.class)) {//校验role输入是否有误
            throw new IllegalArgumentException("角色输入有误");
        }
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userMapper.selectByRole(role);
        users.forEach(user -> {
            userDTOS.add(UserConverter.convertToUserDTO(user));
        });
        PageHelper.startPage(page, pageSize);
        PageInfo<UserDTO> userPageInfo = new PageInfo<>(userDTOS);
        return userPageInfo;
    }

    @Override
    public UserDTOForLogin startLogin(UserDTOForLogin userDTOForLogin) {
        String username = userDTOForLogin.getUsername();
        String password = userDTOForLogin.getPassword();
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("密码不能为空");
        }
        User user = userMapper.selectByStaffIdOrUsername(null, username);
        if (user == null) {
            throw new SourceNotFoundException("用户名输入有误");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("密码输入有误");
        }
        userDTOForLogin.setRole(user.getRole().toUpperCase());
        userDTOForLogin.setLogin(true);
        return userDTOForLogin;
    }


}
