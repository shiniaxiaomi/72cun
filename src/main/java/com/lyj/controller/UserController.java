package com.lyj.controller;

import com.github.pagehelper.PageInfo;
import com.lyj.model.User;
import com.lyj.service.UserService;
import com.lyj.util.Message;
import com.lyj.util.MessageUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Administrator on 2019/4/29.
 */

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;



    @RequestMapping("/getUsers")
    public PageInfo<User> getUsers(int page,int limit){
        return userService.getUsers(page, limit);
    }


    @RequestMapping("/addUser")
    public Message addUser(User user){
        user.setLastLoginTime(new Date());
        userService.addUser(user);

        return MessageUtil.success(null);
    }


    @RequestMapping("/userLogin")
    public Message<User> userLogin(HttpSession session,User user){


        User sessionUser = (User) session.getAttribute("user");
        if(sessionUser!=null){
            return MessageUtil.success(sessionUser);
        }


        User userLogin = userService.userLogin(user);
        session.setAttribute("user",userLogin);//将用户的登入状态保存到session中
        return MessageUtil.success(userLogin);

    }

    @RequestMapping("/exit")
    public Message exit(HttpSession session){
        session.removeAttribute("user");

        return MessageUtil.success("退出成功！");
    }



}
