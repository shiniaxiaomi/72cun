package com.lyj.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyj.dao.AttentionMapper;
import com.lyj.dao.vo.AttentionExtendsMapper;
import com.lyj.exception.MessageException;
import com.lyj.model.Attention;
import com.lyj.model.AttentionExample;
import com.lyj.model.User;
import com.lyj.model.vo.AttentionExtends;
import com.lyj.util.Message;
import com.lyj.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2019/7/6.
 */

@Service
public class AttentionService {

    @Autowired
    AttentionMapper attentionMapper;


    @Autowired
    AttentionExtendsMapper attentionExtendsMapper;


    public int getFansNumber(int attentionUserId) {
        AttentionExample AttentionExample = new AttentionExample();
        AttentionExample.createCriteria().andAttentionUserIdEqualTo(attentionUserId).andStateEqualTo(1);
        return attentionMapper.countByExample(AttentionExample);

    }

    public int getAttentionNumber(int userId) {
        AttentionExample AttentionExample = new AttentionExample();
        AttentionExample.createCriteria().andUserIdEqualTo(userId).andStateEqualTo(1);
        return attentionMapper.countByExample(AttentionExample);
    }

    public PageInfo<AttentionExtends> getFans(HttpSession session, int userId, int page, int limit) {

        PageHelper.startPage(page, limit);
        List<AttentionExtends> fans = attentionExtendsMapper.getFans(userId);

        User user = (User) session.getAttribute("user");
        if(user!=null){
            List<Integer> attentionUsers = attentionExtendsMapper.getAttentionUsers(user.getId());
            //将已经关注的人设置为true
            for(int i=0;i<fans.size();i++){
                if(attentionUsers.contains(fans.get(i).getUserId())){
                    fans.get(i).setAttention(true);
                }
            }
        }

        return new PageInfo<>(fans);
    }

    //先查询已经登入的用户的关注对象，看是否已经存在关注的人
    public PageInfo<AttentionExtends> getAttention(HttpSession session, int userId, int page, int limit) {

        PageHelper.startPage(page, limit);
        List<AttentionExtends> attention = attentionExtendsMapper.getAttention(userId);

        User user = (User) session.getAttribute("user");
        if(user!=null){
            List<Integer> attentionUsers = attentionExtendsMapper.getAttentionUsers(user.getId());
            //将已经关注的人设置为true
            for(int i=0;i<attention.size();i++){
                if(attentionUsers.contains(attention.get(i).getAttentionUserId())){
                    attention.get(i).setAttention(true);
                }
            }
        }

        return new PageInfo<>(attention);
    }

    public void attentionUser(int userId, int attentionUserId) {
        AttentionExample attentionExample = new AttentionExample();
        attentionExample.createCriteria().andUserIdEqualTo(userId).andAttentionUserIdEqualTo(attentionUserId);
        List<Attention> attentions = attentionMapper.selectByExample(attentionExample);
        if(attentions.size()==0){
            Attention Attention=new Attention();
            Attention.setUserId(userId);
            Attention.setAttentionUserId(attentionUserId);
            Attention.setState(1);//1表示关注
            int insert = attentionMapper.insert(Attention);
            if (insert != 1) {
                throw new MessageException("关注失败！");
            }
        }else{
            Attention attention=new Attention();
            attention.setState(1);//关注
            int i = attentionMapper.updateByExampleSelective(attention, attentionExample);
            if(i==0){
                throw new MessageException("关注失败！");
            }
        }

    }

    public boolean isAttentioned(int userId, int attentionUserId) {
        AttentionExample AttentionExample=new AttentionExample();
        AttentionExample.createCriteria().andUserIdEqualTo(userId).andAttentionUserIdEqualTo(attentionUserId).andStateEqualTo(1);
        List<Attention> follows = attentionMapper.selectByExample(AttentionExample);
        if(follows.size()>=1 && follows.get(0).getState()==1){
            return true;
        }else{
            return false;
        }
    }

    //返回用户关注的userId数组
    public List<Integer> getAttentionUsers(int userId) {
        return attentionExtendsMapper.getAttentionUsers(userId);
    }

    //取消关注用户
    public void cancelAttentionUser(int userId, int attentionUserId) {
        AttentionExample attentionExample = new AttentionExample();
        attentionExample.createCriteria().andUserIdEqualTo(userId).andAttentionUserIdEqualTo(attentionUserId).andStateEqualTo(1);
        List<Attention> attentions = attentionMapper.selectByExample(attentionExample);
        if(attentions.size()!=0){
            Attention attention=new Attention();
            attention.setState(0);//取消关注
            int i = attentionMapper.updateByExampleSelective(attention, attentionExample);
            if(i==0){
                throw new MessageException("取消关注失败！");
            }
        }
    }
}
