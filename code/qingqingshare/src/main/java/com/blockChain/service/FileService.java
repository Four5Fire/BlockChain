package com.blockChain.service;

import com.blockChain.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {

    List<FileEntity> getFileListByUsername(String username);

    void addFile(FileEntity onefile, MultipartFile file);

    boolean selectFile(String username,String filename);

    void deleteFile(File file);
}
