package com.blockChain.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
    int selectUserByUserName(@Param("username") String username);
    int selectUserByEmailAddress(@Param("email") String emailAddress);
    int insertUser(@Param("username") String username, @Param("password") String password,
                   @Param("email") String emailAddress);
    int deleteUserByUserName(@Param("username") String username);
}
