package com.blockChain.controller;

import com.sun.deploy.net.HttpResponse;
import lombok.Data;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Data
public class BaseController {
    private HttpServletRequest request;
    private HttpServletResponse response;

}
