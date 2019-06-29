package com.lyj.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer id;

    private String password;

    private String userName;

    private Integer rootFolderId;

    private Integer customFolderId;

    private String customFolderName;

    private Date lastLoginTime;

    private String phoneNumber;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getRootFolderId() {
        return rootFolderId;
    }

    public void setRootFolderId(Integer rootFolderId) {
        this.rootFolderId = rootFolderId;
    }

    public Integer getCustomFolderId() {
        return customFolderId;
    }

    public void setCustomFolderId(Integer customFolderId) {
        this.customFolderId = customFolderId;
    }

    public String getCustomFolderName() {
        return customFolderName;
    }

    public void setCustomFolderName(String customFolderName) {
        this.customFolderName = customFolderName == null ? null : customFolderName.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }
}