package com.wangpeng.test;

import com.wangpeng.pojo.User;
import com.wangpeng.service.UserService;
import com.wangpeng.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(
                new User(null, "bbj168", "666666", "bbj168@qq.com")
        );
        userService.registUser(
                new User(null, "abc168", "666666", "abc168@qq.com")
        );
    }

    @Test
    public void login() {
        System.out.println( userService.login(
                new User(null, "bbj168", "666666", null)
        ));
    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("bbj169")) {
            System.out.println("用户名已存在!");
        } else {
            System.out.println("用户名可用!");
        }
    }
}