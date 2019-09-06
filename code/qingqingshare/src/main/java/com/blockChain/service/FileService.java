package com.blockChain.service;

import com.blockChain.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {

    List<FileEntity> getFileListByUsername(String username,String purview);

    void addFile(FileEntity onefile, MultipartFile file);

    boolean selectFile(String username,String filename);

    void deleteFile(File file);

    void delete(String name,int fileId);

    String getFileName(int fileId);

    FileEntity queryFileByName(String filename);

    byte[] getFile(String filename);

}
