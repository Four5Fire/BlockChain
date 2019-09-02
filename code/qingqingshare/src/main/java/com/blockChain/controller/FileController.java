package com.blockChain.controller;

import com.blockChain.entity.FileEntity;
import com.blockChain.entity.ModelVO;
import com.blockChain.service.FileService;
import com.blockChain.util.ActionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String now=simpleDateFormat.format(date);
        MultipartHttpServletRequest mhr=(MultipartHttpServletRequest)request;
        FileEntity oneFile=new FileEntity();
        oneFile.setFilename(ActionUtil.getStrParam(request,"filename"));
        oneFile.setUsername(ActionUtil.getStrParam(request,"username"));
        MultipartFile multipartFile=mhr.getFile(oneFile.getFilename());
        oneFile.setFilesize(multipartFile.getSize()+"KB");
        oneFile.setCreateTime(now);
        oneFile.setModifyTime(now);

        fileService.addFile(oneFile,multipartFile);
        modelVO.setMsg("上传成功");
        modelVO.setCode(200);
        return modelVO.getResult();
    }

    @RequestMapping(value = "/download" ,method = RequestMethod.POST)
    @ResponseBody
    public HashMap downloadFile(HttpServletRequest request){
        ModelVO modelVO=new ModelVO();
        return modelVO.getResult();
    }



}
