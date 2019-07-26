package com.lyj.model;

import java.io.Serializable;

public class Likes implements Serializable {
    private Integer id;

    private Integer urlId;

    private Integer userId;

    private Integer likesUserId;

    private Integer state;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUrlId() {
        return urlId;
    }

    public void setUrlId(Integer urlId) {
        this.urlId = urlId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLikesUserId() {
        return likesUserId;
    }

    public void setLikesUserId(Integer likesUserId) {
        this.likesUserId = likesUserId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}