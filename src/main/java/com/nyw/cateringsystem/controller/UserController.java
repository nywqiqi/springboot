package com.nyw.cateringsystem.controller;

import com.github.pagehelper.PageInfo;
import com.nyw.cateringsystem.dto.UserDTO;
import com.nyw.cateringsystem.dto.UserDTOForCreat;
import com.nyw.cateringsystem.dto.UserDTOForLogin;
import com.nyw.cateringsystem.result.R;
import com.nyw.cateringsystem.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className UserController
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/users/staffIdOrUsername")
    public R<UserDTO> getUserByStaffIdOrUsername(@RequestParam(defaultValue = "") String staffId,@RequestParam(defaultValue = "") String username) {
        return R.ok(userService.findByStaffIdOrUsername(staffId,username));
    }

    @GetMapping("/users/{id}")
    public R<UserDTO> getUserById(@PathVariable Long id){
        return R.ok(userService.findById(id));
    }

    @PostMapping("/users")
    public R<Long> saveUser(@RequestBody UserDTOForCreat userDTO) {
        return R.ok(userService.insert(userDTO));
    }

    @DeleteMapping("/users")
    public R<Boolean> deleteUser(@RequestParam(defaultValue = "") List<Long> id){
        return R.ok(userService.deleteById(id));
    }

    @PutMapping("/users/info")
    public R<Boolean> updateUserPassword(@RequestParam(defaultValue = "") String staffId,@RequestParam(defaultValue = "") String username,
                                  @RequestParam String password) {
        return R.ok(userService.updatePassword(staffId,username,password));
    }

    @PutMapping("/users")
    public R<Boolean> updateUser(@RequestBody UserDTO userDTO) {
        return R.ok(userService.updatePartOfUser(userDTO));
    }

    @GetMapping("/users")
    public R<PageInfo<UserDTO>> getAllByPage(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
        return R.ok(userService.findAll(pageNum,pageSize));
    }

    @GetMapping("/users/role")
    public R<PageInfo<UserDTO>> getAllByPage(@RequestParam String role, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
        return R.ok(userService.findByRole(role.toUpperCase(),pageNum,pageSize));
    }

    @GetMapping("/users/login")
    public R<String> Login(@RequestBody UserDTOForLogin userDTOForLogin) {
        return R.ok("登录成功","WELCOME! " + userService.startLogin(userDTOForLogin).getRole());
    }
}
