package com.lyj.model.vo;

/**
 * Created by Administrator on 2019/7/6.
 */


import com.lyj.model.Attention;

/**
 * 用于关联查询粉丝用户的姓名
 */
public class AttentionExtends extends Attention{

    String userName;
    String attentionUserName;
    boolean isAttention=false;//是否已经关注(false表示未关注)

    public boolean isAttention() {
        return isAttention;
    }

    public void setAttention(boolean attention) {
        isAttention = attention;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAttentionUserName() {
        return attentionUserName;
    }

    public void setAttentionUserName(String attentionUserName) {
        this.attentionUserName = attentionUserName;
    }
}
