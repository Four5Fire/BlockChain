package com.blockChain.Enum;

public enum CodeEnum {

    SUCCESS(200),PARAM_MISS(401);
    private int code;
    private CodeEnum(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
