package com.blockChain.service.Impl;

import com.blockChain.Enum.CodeEnum;
import com.blockChain.dao.UserDAO;
import com.blockChain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public int addUser(String username, String password, String email) {

        if (userDAO.selectUserByUsername(username) != null){
            return CodeEnum.USERNAME_REGISTRIED.getCode();
        }
        if (userDAO.selectUserByEmail(email) != null){
            return CodeEnum.EMAIL_REGISTRIED.getCode();
        }

        try {
            userDAO.insertUser(username,password,email);
        }catch (Exception e){
            e.printStackTrace();
            return CodeEnum.INNER_ERROR.getCode();
        }

        return CodeEnum.SUCCESS.getCode();
    }
}
