package com.luckypandadas.model.vo;

import com.luckypandadas.model.User;

import java.util.Date;

/**
 * luckypandadas
 * Created by yadong.zhang on com.luckypandadas.model.vo
 * User：yadong.zhang
 * Date：2015/12/29
 * Time：9:36
 */
public class UserVO extends User{
    private User user;
    private String securityCode;//验证码
    private String rePassword;//验证码

    public UserVO(User user) {
        this.user = user;
    }
    public UserVO(User user , String securityCode ,String rePassword){
        this.user = user;
        this.securityCode = securityCode;
        this.rePassword = rePassword;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getRePassword() {
        return rePassword;
    }

    public String getTelephone() {
        return this.user.getTelephone();
    }

    public void setTelephone(String telephone) {
        this.user.setTelephone(telephone);
    }

    public String getNickname() {
        return this.user.getNickname();
    }

    public void setNickname(String nickname) {
        this.user.setNickname(nickname);
    }

    public String getEmail() {
        return this.user.getEmail();
    }

    public void setEmail(String email) {
        this.user.setEmail(email);
    }

    public String getPassword() {
        return this.user.getPassword();
    }

    public void setPassword(String password) {
        this.user.setPassword(password);
    }

    public Date getLastLoginTime() {
        return this.user.getLastLoginTime();
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.user.setLastLoginTime(lastLoginTime);
    }

    public int getGender() {
        return this.user.getGender();
    }

    public void setGender(int gender) {
        this.user.setGender(gender);
    }

    public String getPicturePath() {
        return this.user.getPicturePath();
    }

    public void setPicturePath(String picturePath) {
        this.user.setPicturePath(picturePath);
    }

    public String getId() {
        return this.user.getId();
    }

    public void setId(String id) {
        this.user.setId(id);
    }

    public Date getCreatedTime() {
        return this.user.getCreatedTime();
    }

    public void setCreatedTime(Date createdTime) {
        this.user.setCreatedTime(createdTime);
    }

    public Date getUpdatedTime() {
        return this.user.getUpdatedTime();
    }

    public void setUpdatedTime(Date updatedTime) {
        this.user.setUpdatedTime(updatedTime);
    }

    public Integer getStatus() {
        return this.user.getStatus();
    }

    public void setStatus(Integer status) {
        this.user.setStatus(status);
    }
}
