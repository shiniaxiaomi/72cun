package com.lyj.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyj.dao.LikesMapper;
import com.lyj.dao.vo.UrlExtendsMapper;
import com.lyj.exception.MessageException;
import com.lyj.model.Likes;
import com.lyj.model.LikesExample;
import com.lyj.model.vo.UrlExtends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/7/7.
 */

@Service
public class LikesService {


    @Autowired
    LikesMapper likesMapper;

    @Autowired
    UrlExtendsMapper urlExtendsMapper;

    //保存用户id对应点赞过的url的id，并且每天清空
    Map map=new HashMap();

    //每天零点清空map
    @Scheduled(cron="0 0 0 * * ?")
    private void process(){
        map.clear();
    }

    //对网址进行点赞(userId对应多个urlId)
    public void giveLike(int urlId, int userId,int likesUserId, int state) {

        /**
         * 防止用户对一个网址进行重复的点赞
         */
        Object o = map.get(likesUserId);
        //已经包含，表示该用户已经重复点赞
        if(o!=null){
            if(((List)o).contains(urlId)){
                throw new MessageException("你已经点赞过该网址！");
            }else{
                ((List)o).add(urlId);//如果没有包含，则添加点赞的url的id
            }
        }else {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(urlId);
            map.put(likesUserId,list);
        }


        /**
         * 插入数据
         */
        LikesExample likeExample = new LikesExample();
        likeExample.createCriteria().andUrlIdEqualTo(urlId).andLikesUserIdEqualTo(likesUserId);

        List<Likes> likes = likesMapper.selectByExample(likeExample);
        if(likes.size()==0){
            //插入
            Likes like = new Likes();
            like.setState(state);
            like.setUrlId(urlId);
            like.setUserId(userId);
            like.setLikesUserId(likesUserId);
            int insert = likesMapper.insert(like);//插入数据
            if(insert==0){
                throw new MessageException("点赞失败，请重新再试！");
            }

        }else if(likes.size()==1){
            if(likes.get(0).getState()==1){
                //返回失败
                throw new MessageException("你已经点赞过该网址！");
            }else{
                Likes likes1=new Likes();
                likes1.setState(1);

                likeExample.createCriteria().andUrlIdEqualTo(urlId).andUserIdEqualTo(userId).andStateEqualTo(0);
                likesMapper.updateByExampleSelective(likes1,likeExample);
            }

        }

    }


    //获取用户的被点赞的网址(只能是共享的网址)
    public PageInfo<UrlExtends> getUserLikes(int userId, int state,int page, int limit) {
        PageHelper.startPage(page, limit);
        List<UrlExtends> userLikes = urlExtendsMapper.getUserLikes(userId,state);
        return new PageInfo<>(userLikes);

    }

    //获取用户点赞过的网址
    public PageInfo<UrlExtends> getThisUserLikes(int userId, int state, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<UrlExtends> userLikes = urlExtendsMapper.getThisUserLikes(userId,state);
        return new PageInfo<>(userLikes);
    }

    public int getUserLikesCount(int userId, int state) {
        LikesExample likesExample=new LikesExample();
        likesExample.createCriteria().andUserIdEqualTo(userId).andStateEqualTo(state);
        return likesMapper.countByExample(likesExample);
    }

    public void cancleLike(int likesUserId,int urlId, int state) {
        Likes likes=new Likes();
        likes.setState(0);

        LikesExample likesExample=new LikesExample();
        likesExample.createCriteria().andLikesUserIdEqualTo(likesUserId).andUrlIdEqualTo(urlId).andStateEqualTo(1);
        int i = likesMapper.updateByExampleSelective(likes, likesExample);
        if(i==0){
            throw new MessageException("取消点赞失败！");
        }

        //清空map中对应的网址数据
        Object o = map.get(likesUserId);
        if(o!=null){
            ((List)o).remove(urlId);
        }

    }


    //获取用户点赞别人的网址数量
    public int getThisUserLikesCount(int userId, int state) {
        LikesExample likesExample=new LikesExample();
        likesExample.createCriteria().andLikesUserIdEqualTo(userId).andStateEqualTo(state);
        return likesMapper.countByExample(likesExample);
    }
}
