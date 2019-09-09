package com.blockChain.dao;

import com.blockChain.entity.FileEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDAO {

    List<FileEntity> selectByUsername(String username);

    List<FileEntity> selectAllFile();

    void insertFile(FileEntity fileEntity);

    FileEntity selectFileByUserAndFilename(String username,String filename);

    void deleteOneFile(String name,int fileId);

    String getFileById(int fileId);

    FileEntity queryFileByName(String filename);

    FileEntity selectFileById(int id);
}
