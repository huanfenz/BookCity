package com.wangpeng.test;

import com.wangpeng.dao.UserDao;
import com.wangpeng.dao.impl.UserDaoImpl;
import com.wangpeng.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        if( userDao.queryUserByUsername("admin") == null) {
            System.out.println("用户名可用!");
        } else {
            System.out.println("用户名已存在!");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if( userDao.queryUserByUsernameAndPassword("admin","admin") == null) {
            System.out.println("用户名或密码错误，登陆失败");
        } else {
            System.out.println("登陆成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println( userDao.saveUser(
                new User( null, "wp123", "123456", "wp123@qq.com" )
        ));

    }
}