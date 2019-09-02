package com.blockChain.dao;

import com.blockChain.entity.FileEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDAO {

    List<FileEntity> selectByUsername(String username);

    void insertFile(FileEntity fileEntity);
}
