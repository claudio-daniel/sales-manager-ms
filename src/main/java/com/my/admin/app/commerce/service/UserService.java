package com.my.admin.app.commerce.service;

import com.black.food.manager.dao.UserDao;
import com.black.food.manager.model.api.response.UserResponse;
import com.black.food.manager.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDao userDao;

    public List<User> findAll() { return userDao.findAll(); }

    public UserResponse getUserResponseById(final Long id){
        return userDao.getUserResponseById(id).orElseThrow();
    }

    public Optional<UserResponse> findByUserName(final String userName) { return userDao.findByUserName(userName); }

    @Autowired
    public void setUserDao(final UserDao userDao) {
        this.userDao = userDao;
    }
}
