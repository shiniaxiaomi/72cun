package com.lyj.controller;

import com.github.pagehelper.PageInfo;
import com.lyj.model.User;
import com.lyj.model.vo.AttentionExtends;
import com.lyj.service.AttentionService;
import com.lyj.util.Message;
import com.lyj.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2019/7/6.
 */

@Controller
@RequestMapping("/attention")
public class AttentionController {

    @Autowired
    AttentionService attentionService;


    @ResponseBody
    @RequestMapping("/attentionUser")
    public Message attentionUser(int userId, int attentionUserId){
        attentionService.attentionUser(userId,attentionUserId);
        return MessageUtil.success("关注成功！");
    }
    @ResponseBody
    @RequestMapping("/cancelAttentionUser")
    public Message cancelAttentionUser(int userId, int attentionUserId){
        attentionService.cancelAttentionUser(userId,attentionUserId);
        return MessageUtil.success("取消关注成功！");
    }
    @ResponseBody
    @RequestMapping("/isAttentioned")
    public Message isAttentioned(int userId, int attentionUserId){
        boolean attentioned = attentionService.isAttentioned(userId, attentionUserId);
        if(attentioned){
            return MessageUtil.success("已关注");
        }else{
            return MessageUtil.error("未关注");
        }
    }

    @ResponseBody
    @RequestMapping("/getFansNumber")
    public Message getFansNumber(int attentionUserId){
        int fansNumber = attentionService.getFansNumber(attentionUserId);
        return MessageUtil.success(fansNumber);
    }

    @ResponseBody
    @RequestMapping("/getAttentionNumber")
    public Message getAttentionNumber(int userId){
        int attentionNumber = attentionService.getAttentionNumber(userId);
        return MessageUtil.success(attentionNumber);
    }

    //根据userId获取粉丝
    @ResponseBody
    @RequestMapping("/getFans")
    public PageInfo<AttentionExtends> getFans(HttpSession session,int userId, int page, int limit){
        return attentionService.getFans(session,userId, page, limit);
    }

    //根据要查看的用户的Id获取查看用户的关注的人(并根据已经登入的用户来判断是否已经关注了某些用户)
    @ResponseBody
    @RequestMapping("/getAttention")
    public PageInfo<AttentionExtends> getAttention(HttpSession session,int userId, int page, int limit){

        return attentionService.getAttention(session,userId, page, limit);
    }

    //获取用户所关注的userId
//    @ResponseBody
//    @RequestMapping("/getAttentionUsers")
//    public Message getAttentionUsers(int userId){
//        List<Integer> attentionUser = attentionService.getAttentionUsers(userId);
//        return MessageUtil.success(attentionUser);
//    }


}
