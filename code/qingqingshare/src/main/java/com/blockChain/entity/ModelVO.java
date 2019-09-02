package com.blockChain.entity;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

public class ModelVO {
    private int code;
    private String msg;
    private List data;
    private ResponseEntity responseEntity;
    private HashMap map = new HashMap();

    public void setCode(int code){
        this.code = code;
        map.put("code",code);
    }

    public void setMsg(String msg){
        this.msg = msg;
        map.put("msg",msg);
    }

    public void setData(List list){
        this.data = list;
        map.put("data",data);
    }

    public void setResponseEntity(ResponseEntity responseEntity){
        this.responseEntity=responseEntity;
        map.put("responseEntity",responseEntity);
    }
    public HashMap getResult(){
        return map;
    }


    enum Code{
        SUCCESS(200);
        private int code;
        private Code(int code){
            this.code = code;
        }
    }
}
