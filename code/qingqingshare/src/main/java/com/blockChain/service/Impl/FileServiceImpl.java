package com.blockChain.service.Impl;

import com.blockChain.dao.FileDAO;
import com.blockChain.entity.FileEntity;
import com.blockChain.service.FileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDAO fileDAO;

    @Override
    public List<FileEntity> getFileListByUsername(String username) {
        return fileDAO.selectByUsername(username);
    }

    @Override
    public void addFile(FileEntity onefile, MultipartFile file) {
        new Thread(){
            @Override
            public void run(){
                saveInServer(onefile,file);
            }}.start();
        fileDAO.insertFile(onefile);
    }

    public void saveInServer(FileEntity onefile,MultipartFile file){
        String targetFilepath="./savedFile/"+onefile.getFilename();
        byte[] buffer=new byte[10*1024];
        try {
            InputStream in=file.getInputStream();
            FileOutputStream out=new FileOutputStream(targetFilepath);
            while (in.read(buffer)!=-1){
                out.write(buffer);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
