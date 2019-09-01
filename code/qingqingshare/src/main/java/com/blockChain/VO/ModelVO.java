package com.blockChain.VO;

import java.util.HashMap;
import java.util.List;

public class ModelVO {
    private int code;
    private String msg;
    private List data;
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
