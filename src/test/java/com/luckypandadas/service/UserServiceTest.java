package com.luckypandadas.service;

import com.luckypandadas.common.Constants;
import com.luckypandadas.main.JUnitMainUtil;
import com.luckypandadas.model.User;
import com.luckypandadas.utils.StringBuildUtils;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


/**
 * Created by Innodev-E531 on 2015/12/23.
 */

public class UserServiceTest extends JUnitMainUtil {
    @Autowired
    private IUserService userService;
    @Test
    public void getListTest(){
        try {
            List<User> userList = userService.getList(null);
            for(User user : userList){
                System.err.println("tel------"+user.getTelephone());
                System.err.println("pwd------"+user.getPassword());
                System.err.println("status------"+user.getStatus());
                System.err.println("email------"+user.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveTest(){
        try {
            userService.save(getUser());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void insertTest(){
        try {
            userService.insert(getUser());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void updateTest(){
        try {
            userService.update(getUser());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void updatePasswordTest(){
        try {
            userService.updatePassword("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void updateStatusTest(){
        try {
            userService.updateStatus("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getByTelTest(){
        try {
            User user = userService.getByTel(getUser().getTelephone());
            System.err.println(user.getTelephone());
            System.err.println(user.getEmail());
            System.err.println(user.getNickname());
            System.err.println(user.getPassword());
            System.err.println(user.getId());
            System.err.println(user.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getByEmailTest(){
        User user = null;
        try {
            user = userService.getByEmail("yadong.zhang0415@gmail.com");
            System.err.print(user.getTelephone());
            System.err.print(user.getEmail());
            System.err.print(user.getNickname());
            System.err.print(user.getPassword());
            System.err.print(user.getId());
            System.err.print(user.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void deleteTest(){
        try {
            userService.delete(getUser());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void loginTest(){
        try {
            userService.login("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUser(){
        Date now = new Date();
        User user = new User();
        user.setTelephone("12322241");
        user.setEmail("843977358@qq.com");
        user.setNickname("七彩狼222");
        user.setPassword("123456.");
        user.setId("123413");
        user.setStatus(Constants.STATUS_BLOCK);
        user.setCreatedTime(now);
        user.setUpdatedTime(now);
        user.setLastLoginTime(now);
        return user;
    }

    @Test
    public void aaaaa(){
        String aa = JSONObject.fromObject(getUser()).toString();
        String result = StringBuildUtils.getJsonStr(aa);
        System.err.println(result);
    }
}
