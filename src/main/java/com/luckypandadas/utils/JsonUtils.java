package com.luckypandadas.utils;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * luckypandadas
 * Created by yadong.zhang on com.luckypandadas.utils
 * User：yadong.zhang
 * Date：2015/12/30
 * Time：10:36
 */
public class JsonUtils {

    /**
     * 将json转化为实体POJO
     *
     * @param jsonStr
     * @param obj
     * @return
     */
    public static <T> Object JSONToObj(String jsonStr, Class<T> obj) {
        T t = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            t = objectMapper.readValue(jsonStr, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
