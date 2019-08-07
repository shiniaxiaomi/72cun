package com.lyj.config.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Administrator on 2019/5/6.
 */

/**
 * 跨域访问拦截器
 */

@Component
public class CrossDomainInterceptor implements HandlerInterceptor {


    public static Set allowedOrigins= new HashSet(Arrays.asList(
            "http://www.72cun.cn",
            "http://72cun.cn",
            "https://www.72cun.cn",
            "https://72cun.cn",

            "http://134.175.150.32",//生产服务器ip
            "https://134.175.150.32",

            "http://127.0.0.1:7000",//本地测试
            "http://localhost:7000",
            "https://127.0.0.1:7000",
            "https://localhost:7000",

            "http://www.test.cn"
    ));//多源

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String origin= request.getHeader("origin");
        if(allowedOrigins.contains(origin) || "/url/addUrlFaster".equals(request.getRequestURI()) ){
            //解决跨域问题
            response.setHeader("Access-Control-Allow-Origin",origin);//允许http://localhost:3000这个网址可以跨域访问
            response.setHeader("Access-Control-Allow-Headers","Content-Type, Content-Length, Authorization, Accept, X-Requested-With , yourHeaderFeild");
            response.setHeader("Access-Control-Allow-Methods","POST,GET");
            response.setHeader("Access-Control-Allow-Credentials", "true");//允许携带cookie
            return true;
        }else{
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
