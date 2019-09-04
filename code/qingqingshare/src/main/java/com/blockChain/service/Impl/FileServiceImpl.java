package com.blockChain.service.Impl;

import com.blockChain.dao.FileDAO;
import com.blockChain.entity.FileEntity;
import com.blockChain.service.FileService;
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
    public List<FileEntity> getFileListByUsername(String username,String purview) {
        if("own".equals(purview.trim())){
            return fileDAO.selectByUsername(username);
        }
        return fileDAO.selectAllFile();
    }

    @Override
    public String getFileName(int fileId) {
        return fileDAO.getFileById(fileId).getFilename();
    }

    @Override
    public void delete(String name, int fileId) {
        fileDAO.deleteOneFile(name,fileId);
    }

    @Override
    public void addFile(FileEntity onefile, MultipartFile file) {
        new Thread(){
            @Override
            public void run(){
                saveInServer(file);
            }}.start();
        fileDAO.insertFile(onefile);
    }

    public void saveInServer(MultipartFile file){
        String targetFilepath="C:\\Users\\htj\\Desktop\\test\\"+file.getOriginalFilename();
        try {
            BufferedOutputStream outputStream=new BufferedOutputStream(
                    new FileOutputStream(new File(targetFilepath)));
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean selectFile(String username,String filename){
        if(fileDAO.selectFileByUserAndFilename(username,filename)==null)
            return false;
        return true;
    }

    public void deleteFile(File file){
        if(file.exists()) file.delete();
    }
}
