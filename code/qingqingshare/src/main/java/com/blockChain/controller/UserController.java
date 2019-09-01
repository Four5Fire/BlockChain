package com.blockChain.controller;

import com.blockChain.Enum.CodeEnum;
import com.blockChain.VO.ModelVO;
import com.blockChain.service.UserService;
import com.blockChain.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController extends BaseRequest{

    @Autowired
    private UserService userService;

    @RequestMapping("/regist")
    @ResponseBody
    public HashMap regist(){
        ModelVO modelVO = new ModelVO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String emailAdress = request.getParameter("emailAddress");

        if (StringUtil.isNullOrEmpty(username)||
            StringUtil.isNullOrEmpty(password)||
            StringUtil.isNullOrEmpty(emailAdress)){
            modelVO.setCode(CodeEnum.PARAM_MISS.getCode());
            modelVO.setMsg("参数缺省");
            return modelVO.getResult();
        }


        modelVO.setCode(CodeEnum.SUCCESS.getCode());
        return modelVO.getResult();
    }
}
