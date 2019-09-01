package com.blockChain.util;

import com.blockChain.controller.BaseController;

import javax.servlet.http.HttpServletRequest;

public class ActionUtil extends BaseController {

    public static String getStrParam(HttpServletRequest request,String param){
        String paramter;
        try {
            paramter = request.getParameter(param);
            if ("password".equals(param)){
                paramter = MD5Util.md5(paramter);
            }
        }catch (Exception e){
//            e.printStackTrace();
            paramter = null;
        }
        return paramter;
    }
}
