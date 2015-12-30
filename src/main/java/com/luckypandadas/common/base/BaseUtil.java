package com.luckypandadas.common.base;

import com.luckypandadas.model.User;
import com.luckypandadas.model.vo.UserVO;
import net.sf.json.JSONObject;

/**
 * luckypandadas
 * Created by yadong.zhang on com.luckypandadas.common.base
 * User：yadong.zhang
 * Date：2015/12/30
 * Time：14:50
 */
public class BaseUtil {

    public static UserVO json2User(String param) {
        UserVO userVO = null;
        try {
            JSONObject obj = JSONObject.fromObject(param);
            User user = (User) JSONObject.toBean(JSONObject.fromObject(obj.get("user")), User.class);
            userVO = new UserVO(user, obj.get("securityCode").toString(), obj.get("rePassword").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userVO;
    }
}
