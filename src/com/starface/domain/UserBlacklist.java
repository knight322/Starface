package com.starface.domain;

public class UserBlacklist {
    private Integer id;

    private Integer userId;

    private Integer blacklistUser;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBlacklistUser() {
        return blacklistUser;
    }

    public void setBlacklistUser(Integer blacklistUser) {
        this.blacklistUser = blacklistUser;
    }

}