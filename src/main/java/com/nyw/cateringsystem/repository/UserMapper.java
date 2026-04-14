package com.nyw.cateringsystem.repository;

import com.nyw.cateringsystem.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int insert(User user);

    User selectByStaffIdOrUsername(String staffId, String username);

    User selectById(Long id);

    int deleteByIds(List<Long> ids);

    int update(User user);

    List<User> selectAll();

    List<User> selectByRole(String role);
}