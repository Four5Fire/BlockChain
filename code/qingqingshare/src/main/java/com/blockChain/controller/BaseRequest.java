package com.blockChain.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseRequest {
    public HttpServletRequest request;
    public HttpServletResponse response;

    public void setRequest(HttpServletRequest request){
        this.request = request;
    }

    public HttpServletRequest getRequest(){
        return request;
    }

    public void setResponse(HttpServletResponse response){
        this.response = response;
    }

    public HttpServletResponse getResponse(){
        return response;
    }
}
