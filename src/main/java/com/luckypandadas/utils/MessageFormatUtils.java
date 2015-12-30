package com.luckypandadas.utils;

import java.text.MessageFormat;

/**
 * luckypandadas
 * Created by yadong.zhang on com.luckypandadas.utils
 * User：yadong.zhang
 * Date：2015/12/29
 * Time：10:49
 */
public class MessageFormatUtils {
    private static final String GET_USER_JSON = "user:{0},rePassword:{1},securityCode:{2}";


    /**
     * 格式化获取json字符串
     *
     * @param userStr      用户实体json
     * @param securityCode 验证码
     * @param rePassword   重复密码
     * @return
     */
    public static String getJsonStr(String userStr, String securityCode, String rePassword) {
        return  new StringBuffer().append("{").append(MessageFormat.format(GET_USER_JSON, userStr, rePassword, securityCode)).append("}").toString();
    }

    /**
     * 格式化获取json字符串
     *
     * @param userStr 用户实体json
     * @return
     */
    public static String getJsonStr(String userStr) {
        return new StringBuffer().append("{").append(MessageFormat.format(GET_USER_JSON, userStr, null, null)).append("}").toString();
    }

}