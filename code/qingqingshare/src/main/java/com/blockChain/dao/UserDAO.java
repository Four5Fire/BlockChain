package com.blockChain.dao;

import com.blockChain.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO {
    int insertUser(@Param("username") String username, @Param("password") String password,
                   @Param("email") String emailAddress,@Param("sex")String sex,@Param("grade")String grade);
    int deleteUserByUserName(@Param("username") String username);

    UserEntity selectUserByUsername(@Param("username") String username);

    UserEntity selectUserByEmail(@Param("email") String email);

    int selectUserCountByUsername(@Param("username")String username);

    int updateUserPassword(@Param("username")String username, @Param("password")String password);

    List<UserEntity> selectUserListByUsername(@Param("username")String username,@Param("only")String only);

    List<UserEntity> selectUsers();
}
