package com.wangpeng.service.impl;

import com.wangpeng.dao.UserDao;
import com.wangpeng.dao.impl.UserDaoImpl;
import com.wangpeng.pojo.User;
import com.wangpeng.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        User res = userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        return res;
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryUserByUsername(username) == null) {
            //等于null，说明没查到，表示用户名可用
            return false;
        }
        return true;
    }
}
