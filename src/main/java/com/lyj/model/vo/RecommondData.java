package com.lyj.model.vo;

import com.lyj.model.Url;

/**
 * 封装 推荐数据 的数据类型
 */
public class RecommondData extends Url{

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
