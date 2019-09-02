package com.blockChain.controller;

import com.blockChain.entity.FileEntity;
import com.blockChain.entity.ModelVO;
import com.blockChain.service.FileService;
import com.blockChain.util.ActionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.util.calendar.AbstractCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/file")
public class FileController extends BaseController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/showfile",method = RequestMethod.POST)
    @ResponseBody
    public HashMap getFileByUsername(HttpServletRequest request){
        ModelVO modelVO=new ModelVO();
        String username= ActionUtil.getStrParam(request,"username");
        List<FileEntity> files=fileService.getFileListByUsername(username);
        modelVO.setData(files);
        modelVO.setMsg("成功获取用户文件");
        modelVO.setCode(200);
        return modelVO.getResult();
    }

    @RequestMapping(value = "/upload",method = RequestMethod.PUT)
    @ResponseBody
    public HashMap loadFile(HttpServletRequest request){
        ModelVO modelVO=new ModelVO();
        modelVO.setCode(200);
        //获取用户明
        String username=ActionUtil.getStrParam(request,"username");
        //获取文件
        MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest) request;
        MultipartFile file=multipartHttpServletRequest.getFile("upload");
        //获取时间
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String now=simpleDateFormat.format(date);
        //获取文件明
        String filename=file.getOriginalFilename();
        FileEntity oneFile=new FileEntity();
        if(fileService.selectFile(username,filename)){
            modelVO.setMsg("已存在同样的文件");
            modelVO.setCode(400);
            return modelVO.getResult();
        }
        oneFile.setFilename(filename);
        oneFile.setFilesize(file.getSize()+"B");
        oneFile.setCreateTime(now);
        oneFile.setModifyTime(now);
        oneFile.setUsername(username);
        fileService.addFile(oneFile,file);
        modelVO.setMsg("上传成功");

        return modelVO.getResult();
    }

    @RequestMapping(value = "/download" ,method = RequestMethod.POST)
    @ResponseBody
    public HashMap downloadFile(HttpServletRequest request,HttpServletResponse response) {

        ModelVO modelVO=new ModelVO();
        modelVO.setCode(200);
        String filename= ActionUtil.getStrParam(request,"filename");
        //设置获取的文件路径（本地）
        String filepath="C:\\Users\\htj\\Desktop\\test\\"+filename;
        File file=new File(filepath);
       //设置response
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        response.setContentLength((int)file.length());
        response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
        //设置返回信息
        try {
            byte[] buffer=new byte[10*1024];
            BufferedInputStream inputStream=new BufferedInputStream(
                    new FileInputStream(file));
            OutputStream outputStream=response.getOutputStream();
            while (inputStream.read(buffer)!=-1){
                outputStream.write(buffer);
            }
            outputStream.close();
            inputStream.close();
            modelVO.setMsg("文件下载成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelVO.getResult();
    }

}
