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
    public int addUser(String username, String password, String emailAddress) {

        if (userDAO.selectUserByUserName(username) > 0){
            return CodeEnum.USERNAME_REGISTRIED.getCode();
        }
        if (userDAO.selectUserByEmailAddress(emailAddress) > 0){
            return CodeEnum.EMAIL_REGISTRIED.getCode();
        }

        try {
            userDAO.insertUser(username,password,emailAddress);
        }catch (Exception e){
            e.printStackTrace();
            return CodeEnum.INNER_ERROR.getCode();
        }

        return CodeEnum.SUCCESS.getCode();
    }
}
