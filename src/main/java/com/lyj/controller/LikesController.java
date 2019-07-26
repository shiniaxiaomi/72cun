package com.lyj.controller;

import com.github.pagehelper.PageInfo;
import com.lyj.model.vo.UrlExtends;
import com.lyj.service.LikesService;
import com.lyj.util.Message;
import com.lyj.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2019/7/7.
 */

/**
 * 点赞的Controller
 */

@Controller
@RequestMapping("/like")
public class LikesController {

    @Autowired
    LikesService likesService;

    //点赞
    @ResponseBody
    @RequestMapping("/giveLike")
    public Message giveLike(int urlId, int userId,int likesUserId, int state){
        likesService.giveLike(urlId,userId,likesUserId,state);
        return MessageUtil.success("点赞成功！");
    }

    //获取用户的点赞信息
    @ResponseBody
    @RequestMapping("/getUserLikes")
    public PageInfo<UrlExtends> getUserLikes(int userId, int state,int page, int limit){
        return likesService.getUserLikes(userId, state,page,limit);
    }

    //获取用户的已点赞过的数量
    @ResponseBody
    @RequestMapping("/getUserLikesCount")
    public Message getUserLikesCount(int userId, int state){
        int userLikesCount = likesService.getUserLikesCount(userId, state);
        return MessageUtil.success(userLikesCount);
    }

    //取消点赞
    @ResponseBody
    @RequestMapping("/cancleLike")
    public Message cancleLike(int likesUserId,int urlId, int state){
        likesService.cancleLike(likesUserId,urlId, state);
        return MessageUtil.success("点赞取消成功！");
    }

    //获取对应用户的点赞别人的url
    @ResponseBody
    @RequestMapping("/getThisUserLikes")
    public PageInfo<UrlExtends> getThisUserLikes(int userId, int state,int page, int limit){
        return likesService.getThisUserLikes(userId, state,page,limit);
    }

    @ResponseBody
    @RequestMapping("/getThisUserLikesCount")
    public Message getThisUserLikesCount(int userId, int state){
        int thisUserLikesCount = likesService.getThisUserLikesCount(userId, state);
        return MessageUtil.success(thisUserLikesCount);
    }

}
