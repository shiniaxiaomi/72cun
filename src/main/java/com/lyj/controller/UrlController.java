package com.lyj.controller;

import com.github.pagehelper.PageInfo;
import com.lyj.exception.MessageException;
import com.lyj.model.Url;
import com.lyj.model.User;
import com.lyj.service.UrlService;
import com.lyj.service.UserService;
import com.lyj.util.BASE64Util;
import com.lyj.util.Message;
import com.lyj.util.MessageUtil;
import com.lyj.model.vo.UrlExtends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2019/4/29.
 */

@Controller
@RequestMapping("/url")
public class UrlController {

    public Random random=new Random();


    @Autowired
    UrlService urlService;

    @Autowired
    UserService userService;



    @ResponseBody
    @RequestMapping("/getUrls")
    public PageInfo<Url> getUrls(HttpSession session,int page, int limit){
        User user = (User) session.getAttribute("user");
        return urlService.getUrls(user.getId(),page, limit);
    }


    @ResponseBody
    @RequestMapping("/searchByUserId")
    public PageInfo<Url> search(int userId, String keyword, int page, int limit){
        return urlService.search(userId, keyword, page, limit);
    }

    //无需登入
    @ResponseBody
    @RequestMapping("/search")
    public PageInfo<Url> search(String mark, String keyword, int page, int limit){
        String s = null;
        try {
            s = BASE64Util.decryptBASE64(mark);
        } catch (Exception e) {
            throw new MessageException("网址添加失败:标志错误");
        }

        if(s.length()<=6){
            throw new MessageException("网址添加失败:请先登入，获取唯一的快速添加工具，并避免泄露给他人!");
        }

        int userId = Integer.valueOf(s.substring(3, s.length() - 3));

        return urlService.search(userId, keyword, page, limit);
    }

    @ResponseBody
    @RequestMapping("/updateUrlById")
    public Message updateUrlById(Url url){

        urlService.updateUrlById(url);

        return MessageUtil.success("网址更新成功！");

    }


    @ResponseBody
    @RequestMapping("/deleteUrlById")
    public Message deleteUrlById(int id){
        urlService.deleteUrlById(id);
        return MessageUtil.success("网址删除成功！");

    }

    @ResponseBody
    @RequestMapping("/getUrlsByFolderId")
    public PageInfo<Url> getUrlsByFolderId(int pid,int page, int limit){
        return urlService.getUrlsByFolderId(pid, page, limit);
    }

    @ResponseBody
    @RequestMapping("/searchInFolder")
    public PageInfo<Url> searchInFolder(int userId,String keyword,int pid,int page, int limit){
        return urlService.searchInFolder(userId,keyword,pid, page, limit);
    }

    @ResponseBody
    @RequestMapping("/addUrl")
    public Message addUrl(HttpSession session,Url url){
        User user = (User) session.getAttribute("user");
        url.setUserId(user.getId());
        url.setCreateTime(new Date());
        urlService.addUrl(url);
        return MessageUtil.success("网址添加成功！");

    }

    //获取user的对应登入标识
    //加密算法:随机生成两个三位数加在userId的前后
    @ResponseBody
    @RequestMapping("/getMark")
    public String getMark(HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        String s = String.valueOf(random.nextInt(800000) + 100000);
        String mark = s.substring(0, 3) + user.getId() + s.substring(3, s.length());
        return BASE64Util.encryptBASE64(mark);
    }


    //无需登入
    //获取user的对应登入标识
    //加密算法:随机生成两个三位数加在userId的前后
    @RequestMapping("/addUrlFaster")
    public ModelAndView addUrlFaster(Url url, String mark) {
        String s = null;
        try {
            s = BASE64Util.decryptBASE64(mark);
        } catch (Exception e) {
            throw new MessageException("网址添加失败:标志错误");
        }

        if(s.length()<=6){
            throw new MessageException("网址添加失败:请先登入，获取唯一的快速添加工具，并避免泄露给他人!");
        }

        int userId = Integer.valueOf(s.substring(3, s.length() - 3));
        User user = userService.getUserById(userId);

        if(user==null){
            throw new MessageException("网址添加失败:请先登入，获取唯一的快速添加工具，并避免泄露给他人!");
        }

        url.setUserId(userId);
        url.setCreateTime(new Date());
        url.setPidName(user.getCustomFolderName());
        url.setPid(user.getCustomFolderId());

        urlService.addUrl(url);
        return new ModelAndView("addurlfaster");


    }


    //-----以下是首页的一些请求----
    @ResponseBody
    @RequestMapping("/getRecommondData")
    public PageInfo<UrlExtends> getRecommondData(int limit)  {
        return urlService.getRecommondData(limit);
    }

    @ResponseBody
    @RequestMapping("/getSearchData")
    public PageInfo<UrlExtends> getSearchData( String keyword, int page, int limit){
        return urlService.getSearchData(keyword, page, limit);
    }
}
