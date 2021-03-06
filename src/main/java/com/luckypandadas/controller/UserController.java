package com.luckypandadas.controller;

import com.luckypandadas.common.*;
import com.luckypandadas.common.ResponseStatus;
import com.luckypandadas.common.annotation.LoggerAnnotation;
import com.luckypandadas.exception.NoticeException;
import com.luckypandadas.model.User;
import com.luckypandadas.service.IUserService;
import com.luckypandadas.utils.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * luckypandadas
 * Created by yadong.zhang on com.luckypandadas.controller
 * User：yadong.zhang
 * Date：2015/12/24
 * Time：13:05
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @LoggerAnnotation(level = 1, className = "UserController", methodName = "goLogin")
    @RequestMapping("/goLogin")
    public ModelAndView goLogin() {
        ModelAndView mv = new ModelAndView("/login");
        mv.addObject("tag", 1);
        return mv;
    }

    @LoggerAnnotation(level = 1, className = "UserController", methodName = "goSignup")
    @RequestMapping("/goSignup")
    public ModelAndView goSignup() {
        ModelAndView mv = new ModelAndView("/login");
        mv.addObject("tag", 2);
        return mv;
    }

    @LoggerAnnotation(level = 1, className = "UserController", methodName = "goUpdatePassword")
    @RequestMapping("/goUpdatePassword/{tel}")
    public ModelAndView goUpdatePassword(@PathVariable("tel") String tel) {
        ModelAndView mv = new ModelAndView("/update");
        mv.addObject("tag", 2);
        try {
            mv.addObject("user", userService.getByTel(tel));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    @LoggerAnnotation(level = 1, className = "UserController", methodName = "goUpdateStatus")
    @RequestMapping("/goUpdateStatus/{tel}")
    public ModelAndView goUpdateStatus(@PathVariable("tel") String tel) {
        ModelAndView mv = new ModelAndView("/update");
        mv.addObject("tag", 1);
        try {
            mv.addObject("user", userService.getByTel(tel));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    @LoggerAnnotation(level = 1, className = "UserController", methodName = "login")
    @RequestMapping("/login")
    @ResponseBody
    public ResponseVo login(String param) {
        try {
            return userService.login(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseVo(ResponseStatus.ERROR);
        }
    }

    @LoggerAnnotation(level = 1, className = "UserController", methodName = "signUp")
    @RequestMapping("/signUp")
    @ResponseBody
    public ResponseVo signUp(String param) {
        try {
            return userService.signUp(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseVo(ResponseStatus.ERROR);
        }
    }

    @LoggerAnnotation(level = 1, className = "UserController", methodName = "modify")
    @RequestMapping("/modify")
    @ResponseBody
    public ResponseVo modify(String param) {
        try {
            return userService.modify(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseVo(ResponseStatus.ERROR);
        }
    }

    @LoggerAnnotation(level = 1, className = "UserController", methodName = "updatePassword")
    @RequestMapping("/updatePassword")
    @ResponseBody
    public ResponseVo updatePassword(String param) {
        try {
            return userService.updatePassword(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseVo(ResponseStatus.ERROR);
        }
    }

    @LoggerAnnotation(level = 1, className = "UserController", methodName = "updateStatus")
    @RequestMapping("/updateStatus")
    @ResponseBody
    public ResponseVo updateStatus(String param) {
        try {
            return userService.updateStatus(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseVo(ResponseStatus.ERROR);
        }
    }
}
