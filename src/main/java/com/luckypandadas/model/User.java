package com.luckypandadas.model;

import com.luckypandadas.common.Constants;
import com.luckypandadas.common.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

/**
 * Created by zhangyadong on 2015/12/23.
 */
@Document(collection = "user")
public class User extends BaseModel {
    private String telephone;//手机
    private String nickname;//昵称
    private String email;// 邮箱
    private String password;// 密码
    private Date lastLoginTime;// 最后一次登录时间
    private int gender; // 性别
    private String picturePath;// 头像路径

    public User(){
    }

    public User(String telephone,String password){
        this.createdTime = new Date();
        this.updatedTime = new Date();
        this.status = Constants.STATUS_NOMAL;
        this.gender = Constants.GENDER_M;
        this.telephone = telephone;
        this.password = password;
        this.picturePath = "";
    }

    @Override
    public String toString() {
        return "User{" +
                "telephone='" + getTelephone() + '\'' +
                ", nickname='" + getNickname() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", lastLoginTime=" + getPassword() +
                ", gender=" + getGender() +
                ", picturePath='" + getPicturePath() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getGender() == user.getGender() &&
                Objects.equals(getTelephone(), user.getTelephone()) &&
                Objects.equals(getNickname(), user.getNickname()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getLastLoginTime(), user.getLastLoginTime()) &&
                Objects.equals(getPicturePath(), user.getPicturePath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTelephone(), getNickname(), getEmail(), getPassword(), getLastLoginTime(), getGender(), getPicturePath());
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
