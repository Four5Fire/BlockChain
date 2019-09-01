package com.blockChain.Enum;

public enum CodeEnum {

    SUCCESS(200),USERNAME_REGISTRIED(401),EMAIL_REGISTRIED(402),PARAM_MISS(405),
    INNER_ERROR(406);
    private int code;
    private CodeEnum(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
