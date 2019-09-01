package com.blockChain.util;

import com.blockChain.controller.BaseController;

import javax.servlet.http.HttpServletRequest;

public class ActionUtil extends BaseController {

    public static String getStrParam(HttpServletRequest request,String param){
        String paramter;
        try {
            paramter = request.getParameter(param);
        }catch (Exception e){
//            e.printStackTrace();
            paramter = null;
        }
        return paramter;
    }
}
