package com.troyboot.java.system.service;

import com.troyboot.java.system.dao.UserDao;
import com.troyboot.java.system.po.RolePo;
import com.troyboot.java.system.po.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Authour YangYang
 * @Date 2018/6/7 10:43
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<UserPo> getUsersByName(String name){
        return userDao.findByName(name);
    }

    public UserPo getUserByAccount(String account){
        return userDao.findByAccount(account);
    }

    public UserPo getUserById(int id){
        return userDao.findById(id);
    }

    public List<UserPo> getAllUsers(){
        return userDao.findAll();
    }
}
