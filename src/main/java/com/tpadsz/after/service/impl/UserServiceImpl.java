package com.tpadsz.after.service.impl;

import com.tpadsz.after.dao.UserDao;
import com.tpadsz.after.entity.User;
import com.tpadsz.after.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by after on 2018/8/5.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAll() {
        userDao.getAll();
        return  userDao.getAll();
    }
}
