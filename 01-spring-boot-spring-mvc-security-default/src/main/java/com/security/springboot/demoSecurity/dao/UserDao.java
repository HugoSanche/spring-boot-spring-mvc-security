package com.security.springboot.demoSecurity.dao;
import com.security.springboot.demoSecurity.entity.*;
public interface UserDao {

    User findByUserName(String userName);

    void save(User theUser);
}
