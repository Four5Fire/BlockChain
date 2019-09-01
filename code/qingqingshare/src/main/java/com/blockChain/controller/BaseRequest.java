package com.blockChain.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpRequest;

public class BaseRequest {
    private HttpRequest request;
    private HttpResponse response;

    public void setRequest(HttpRequest request){
        this.request = request;
    }

    public HttpRequest getRequest(HttpRequest request){
        return request;
    }

    public void setResponse(HttpResponse response){
        this.response = response;
    }

    public HttpResponse getResponse(HttpResponse response){
        return response;
    }
}
