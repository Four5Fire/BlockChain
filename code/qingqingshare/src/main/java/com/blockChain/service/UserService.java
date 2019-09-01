package com.blockChain.service;

import org.springframework.stereotype.Service;


public interface UserService {

    int addUser(String username, String password, String email);

    int checkUser(String username, String password);
}
