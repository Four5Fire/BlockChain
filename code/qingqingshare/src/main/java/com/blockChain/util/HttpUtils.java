package com.blockChain.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class HttpUtils {

    private static HttpClient client=null;
    private static HttpPost httpPost=null;
    private static long startTime=0L;
    private static long endTime=0L;
    private static int status=0;

    //进行参数初始化操作
    static {
        client= HttpClients.createDefault();
        httpPost=new HttpPost();
    }

    //封装http的post请求
    public static String httpPostUtil(String ip,int port,String params){
        String url="http://"+ip+":"+port+"/?";
        String body=null;//请求体
        //创建请求
        httpPost.addHeader("Content-type","application/json; charset=utf-8");
        httpPost.setHeader("Accept","application/json");
        httpPost.setEntity(new StringEntity(params, Charset.forName("UTF-8")));

        startTime=System.currentTimeMillis();
        try {
            HttpResponse response=client.execute(httpPost);
            endTime=System.currentTimeMillis();
            System.out.println("runTime="+(endTime-startTime));
            body= EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
       return body;
    }
}
