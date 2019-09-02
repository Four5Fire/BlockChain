package com.blockChain.service;

import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    int addUser(String username, String password, String email,String sex, String grade);

    int checkUser(String username, String password);

    int resetUser(String username, String password, String email);

    List selectUserByUsername(String username,String only);
}
