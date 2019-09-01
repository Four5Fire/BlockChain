package com.blockChain.controller;

import com.blockChain.Enum.CodeEnum;
import com.blockChain.entity.ModelVO;
import com.blockChain.service.UserService;
import com.blockChain.util.ActionUtil;
import com.blockChain.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    @ResponseBody
    public HashMap regist(HttpServletRequest request){
        ModelVO modelVO = new ModelVO();
        String username = ActionUtil.getStrParam(request,"username");
        String password = ActionUtil.getStrParam(request,"password");
        String email = ActionUtil.getStrParam(request,"emailAdress");
        System.out.println(email);
        if (StringUtil.isNullOrEmpty(username)||
            StringUtil.isNullOrEmpty(password)||
            StringUtil.isNullOrEmpty(email)){
            modelVO.setCode(CodeEnum.PARAM_MISS.getCode());
            modelVO.setMsg("参数缺省");
            logger.info("用户注册参数缺省");
            return modelVO.getResult();
        }

        int result = userService.addUser(username,password,email);
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

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public HashMap login(HttpServletRequest request){
        ModelVO modelVO = new ModelVO();
        String username = ActionUtil.getStrParam(request,"username");
        String password = ActionUtil.getStrParam(request,"password");

        if (StringUtil.isNullOrEmpty(username)||
                StringUtil.isNullOrEmpty(password)){
            modelVO.setCode(CodeEnum.PARAM_MISS.getCode());
            modelVO.setMsg("参数缺省");
            logger.info("用户注册参数缺省");
            return modelVO.getResult();
        }
        int result = userService.checkUser(username,password);
        switch (result){
            case 200:
                modelVO.setCode(CodeEnum.SUCCESS.getCode());
                modelVO.setMsg("用户登录成功");
                break;
            case 403:
                modelVO.setCode(CodeEnum.USER_CHECK_FAILED.getCode());
                modelVO.setMsg("密码错误");
                logger.info(username+":密码错误");
                break;
            case 407:
                modelVO.setCode(CodeEnum.USER_NOT_EXIST.getCode());
                modelVO.setMsg("用户未注册");
                logger.info(username+":该用户未注册");
            default:
                modelVO.setCode(result);
        }
        return modelVO.getResult();
    }

    @RequestMapping(value = "/reset",method = RequestMethod.POST)
    @ResponseBody
    public HashMap resetPassword(HttpServletRequest request){
        ModelVO modelVO = new ModelVO();
        String username = ActionUtil.getStrParam(request,"username");
        String password = ActionUtil.getStrParam(request,"password");
        String email = ActionUtil.getStrParam(request,"emailAddress");

        if (StringUtil.isNullOrEmpty(username)||
                StringUtil.isNullOrEmpty(password)||
                StringUtil.isNullOrEmpty(email)){
            modelVO.setCode(CodeEnum.PARAM_MISS.getCode());
            modelVO.setMsg("参数缺省");
            logger.info("用户注册参数缺省");
            return modelVO.getResult();
        }
        int result = userService.resetUser(username,password,email);
        switch (result){
            case 200:
                modelVO.setCode(CodeEnum.SUCCESS.getCode());
                modelVO.setMsg("密码重置成功");
                break;
            case 403:
                modelVO.setCode(CodeEnum.USER_CHECK_FAILED.getCode());
                modelVO.setMsg("信息校验失败");
                logger.info(username+":信息校验失败");
                break;
            case 407:
                modelVO.setCode(CodeEnum.USER_NOT_EXIST.getCode());
                modelVO.setMsg("用户未注册");
                logger.info(username+":该用户未注册");
            default:
                modelVO.setCode(result);
        }
        return modelVO.getResult();
    }

    @RequestMapping(value = "/query",method = RequestMethod.POST)
    @ResponseBody
    public HashMap userQuery(HttpServletRequest request){
        ModelVO modelVO = new ModelVO();
        String username = ActionUtil.getStrParam(request,"username");
        String only = ActionUtil.getStrParam(request,"only");

        if (StringUtil.isNullOrEmpty(username)||
            StringUtil.isNullOrEmpty(only)){
            modelVO.setCode(CodeEnum.PARAM_MISS.getCode());
            modelVO.setMsg("参数缺省");
            logger.info("用户注册参数缺省");
            return modelVO.getResult();
        }

        if ("true".equals(only)&&"false".equals(only)){
            modelVO.setCode(CodeEnum.PARAM_MISS.getCode());
            modelVO.setMsg("查询规则不存在");
            logger.info("查询规则不存在");
            return modelVO.getResult();
        }

        modelVO.setCode(CodeEnum.SUCCESS.getCode());
        modelVO.setMsg("查询成功");
        modelVO.setData(userService.selectUserByUsername(username,only));
        return modelVO.getResult();
    }
}
