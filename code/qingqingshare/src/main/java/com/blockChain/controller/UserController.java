package com.blockChain.controller;

import com.blockChain.Enum.CodeEnum;
import com.blockChain.VO.ModelVO;
import com.blockChain.service.UserService;
import com.blockChain.util.ActionUtil;
import com.blockChain.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/regist")
    @ResponseBody
    public HashMap regist(HttpServletRequest request){
        ModelVO modelVO = new ModelVO();
        String username = ActionUtil.getStrParam(request,"username");
        String password = ActionUtil.getStrParam(request,"password");
        String emailAdress = ActionUtil.getStrParam(request,"emailAddress");

        if (StringUtil.isNullOrEmpty(username)||
            StringUtil.isNullOrEmpty(password)||
            StringUtil.isNullOrEmpty(emailAdress)){
            modelVO.setCode(CodeEnum.PARAM_MISS.getCode());
            modelVO.setMsg("参数缺省");
            logger.info("用户注册参数缺省");
            return modelVO.getResult();
        }

        int result = userService.addUser(username,password,emailAdress);
        switch (result){
            case 200:
                modelVO.setCode(CodeEnum.SUCCESS.getCode());
                modelVO.setMsg("用户注册成功");
                break;
            case 401:
                modelVO.setCode(CodeEnum.USERNAME_REGISTRIED.getCode());
                modelVO.setMsg("用户名已注册");
                logger.info("用户名已注册");
                break;
            case 402:
                modelVO.setCode(CodeEnum.EMAIL_REGISTRIED.getCode());
                modelVO.setMsg("电子邮箱已注册");
                logger.info("电子邮箱已注册");
                break;
            default:
                modelVO.setCode(result);
        }

        return modelVO.getResult();
    }
}
