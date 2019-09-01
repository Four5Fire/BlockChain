package com.blockChain.Enum;

public enum CodeEnum {

    SUCCESS(200),USERNAME_REGISTRIED(401),EMAIL_REGISTRIED(402),
    USER_CHECK_FAILED(403),PARAM_MISS(405), INNER_ERROR(406),
    USER_NOT_EXIST(407);
    private int code;
    private CodeEnum(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
