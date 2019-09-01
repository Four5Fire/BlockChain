package com.blockChain.util;

public class StringUtil {

    public static boolean isNullOrEmpty(String str){
        if (str.isEmpty()||str == null){
            return true;
        }else {
            return false;
        }
    }
}
