package com.blockChain.controller;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Data
public class BaseController {
    private HttpServletRequest request;
    private HttpServletResponse response;

}
