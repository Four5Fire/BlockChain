package com.blockChain.config;

import com.blockChain.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
      //  LoginInterceptor loginInterceptor=new LoginInterceptor();
       // InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
        //拦截路径，默认拦截所有请求
       // loginRegistry.addPathPatterns("/**");
        //排除路径
       // loginRegistry.excludePathPatterns("/user/reset","/user/login","/user/regist");
    }
}
