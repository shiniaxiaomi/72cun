package com.lyj.config.interceptor;

import com.lyj.model.User;
import com.lyj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 陆英杰
 * 2018/9/25 14:02
 */

/**
 * 登入拦截器
 */

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 在请求前处理,如果返回true,则继续进行拦截器调用,否则,直接退出拦截器,返回对应的结果
     * response.sendRedirect("/index.html");//url: http://localhost:8087/index.html
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user==null){ //去登入
            System.out.println(request.getRequestURI()+"被拦截");
            return false;
        }

        return true;


    }

    //对请求进行处理
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    //在请求后处理
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
