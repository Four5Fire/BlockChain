package com.blockChain.service.Impl;

import com.blockChain.Enum.CodeEnum;
import com.blockChain.dao.UserDAO;
import com.blockChain.entity.UserEntity;
import com.blockChain.entity.UserVO;
import com.blockChain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Override
    public int addUser(String username, String password, String email,String sex, String grade) {

        if (userDAO.selectUserByUsername(username) != null) {
            return CodeEnum.USERNAME_REGISTRIED.getCode();
        }
        if (userDAO.selectUserByEmail(email) != null) {
            return CodeEnum.EMAIL_REGISTRIED.getCode();
        }

        try {
            userDAO.insertUser(username, password, email,sex,grade);
        } catch (Exception e) {
            e.printStackTrace();
            return CodeEnum.INNER_ERROR.getCode();
        }

        return CodeEnum.SUCCESS.getCode();
    }

    @Override
    public int checkUser(String username, String password) {

        if (userDAO.selectUserCountByUsername(username) <= 0) {
            return CodeEnum.USER_NOT_EXIST.getCode();
        }

        UserEntity user;
        try {
            user = userDAO.selectUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return CodeEnum.INNER_ERROR.getCode();
        }
        if (!password.equals(user.getPassword())) {
            return CodeEnum.USER_CHECK_FAILED.getCode();
        }
        return CodeEnum.SUCCESS.getCode();
    }

    @Override
    public int resetUser(String username, String password, String email) {

        if (userDAO.selectUserCountByUsername(username) <= 0) {
            return CodeEnum.USER_NOT_EXIST.getCode();
        }

        UserEntity user;
        try {
            user = userDAO.selectUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return CodeEnum.INNER_ERROR.getCode();
        }

        if (!email.equals(user.getEmail())) {
            return CodeEnum.USER_CHECK_FAILED.getCode();
        }

        try {
            userDAO.updateUserPassword(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return CodeEnum.INNER_ERROR.getCode();
        }
        return CodeEnum.SUCCESS.getCode();
    }

    @Override
    public List selectUserByUsername(String username, String only) {

        List<UserEntity> userEntityList = null;

        if ("".equals(username)){
            userEntityList = userDAO.selectUsers();
            List userVOList = new ArrayList<UserVO>();
            for (UserEntity userEntity:userEntityList) {
                UserVO userVO = new UserVO();
                userVO.setUsername(userEntity.getUsername());
                userVO.setUserPic("https://s2.ax1x.com/2019/09/01/nptJC4.jpg");
                userVOList.add(userVO);
            }
            return userVOList;
        }

        try{
            userEntityList = userDAO.selectUserListByUsername(username,only);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("查询"+username+"出错");
        }

        List userVOList = new ArrayList<UserVO>();
        for (UserEntity userEntity:userEntityList) {
            UserVO userVO = new UserVO();
            userVO.setUsername(userEntity.getUsername());
            userVO.setUserPic("https://s2.ax1x.com/2019/09/01/nptJC4.jpg");
            userVOList.add(userVO);
        }

        return userVOList;
    }
}
