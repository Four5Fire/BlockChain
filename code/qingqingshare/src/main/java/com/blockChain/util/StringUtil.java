package com.blockChain.util;

public class StringUtil {

    public static boolean isNullOrEmpty(String str){
        if (str == null || str.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
}
