package com.nyw.cateringsystem.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className PasswordTemp
 */
public class PasswordTemp {

    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static void main(String[] args) {
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }
}
