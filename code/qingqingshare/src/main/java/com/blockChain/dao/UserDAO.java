package com.blockChain.dao;

import com.blockChain.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
    int insertUser(@Param("username") String username, @Param("password") String password,
                   @Param("email") String emailAddress);
    int deleteUserByUserName(@Param("username") String username);

    UserEntity selectUserByUsername(@Param("username") String username);

    UserEntity selectUserByEmail(@Param("email") String email);
}
