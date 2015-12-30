package com.luckypandadas.utils;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 针对String字符串的验证以及操作.
 * 
 * @author (作者) zhangyd-c 2015-1-8 下午12:48:56
 * @version (版本) V1.0
 * @since (该版本支持的JDK版本) ： 1.7
 */
public class StringUtil {
    
    /**
     * @Description 判断对象是否为空
     * @author zhangyd-c
     * @date 2015年9月8日 上午10:37:44
     * @param obj
     * @return 如果obj为空或者为''返回true，否则返回false
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        // 字符串类型
        else if (obj instanceof String) {
            return obj.toString().trim().length() <= 0 ? true : false;
        }
        // list类型
        else if (obj instanceof List<?>) {
            return ((List<?>)obj).size() <= 0 ? true : false;
        }
        // 数组类型
        else if (obj.getClass().isArray()) {
            return Array.getLength(obj) <= 0 ? true : false;
        }
        // 集合类型
        else if (obj instanceof Collection) {
            return ((Collection<?>)obj).size() <= 0 ? true : false;
        }
        // map类型
        else if (obj instanceof Map) {
            return ((Map<?, ?>)obj).size() <= 0 ? true : false;
        }
        // 文件类型
        else if (obj instanceof File) {
            return ((File)obj).exists();
        }
        return false;
    }
    
    /**
     * @Description 判断对象是否不为空
     * @author mengLei
     * @date 2015-2-4 上午11:58:51
     * @param obj
     * @return 如果obj不为空并且不为''返回true，否则返回false
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
    
    /**
     * @Description 判断字符串是否为null或者''或者为"null"
     * @author zhangyd-c
     * @date 2015年9月2日 下午5:20:37
     * @param obj
     * @return 如果字符串为null、''或者"null"时返回true，否则返回false
     */
    public static boolean isNullStr(String obj) {
        if (isEmpty(obj) || "null".equals(obj.trim())) {
            return true;
        }
        return false;
    }
    
    /**
     * @Description 判断字符串是否不为空并且不为"null"
     * @author zhangyd-c
     * @date 2015年9月8日 上午10:40:51
     * @param obj
     * @return
     */
    public static boolean isNotStrNull(String obj) {
        if (isNotEmpty(obj) && !"null".equals(obj.trim())) {
            return true;
        }
        return false;
    }
    
    /**
     * @Description 首字母小写
     * @author zhangyd-c
     * @date 2015年9月8日 上午10:53:58
     * @param str
     * @return
     */
    public static String firstLetter2Lower(String str) {
        if (isNotEmpty(str)) {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        }
        return str;
    }
    
    /**
     * @Description 首字母大写
     * @author zhangyd-c
     * @date 2015年9月8日 上午10:53:58
     * @param str
     * @return
     */
    public static String firstLetter2UpperCase(String str) {
        if (isNotEmpty(str)) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return str;
    }
    
    /**
     * @Description 自动在str后补充(length-str.length())个symbol<br>
     *              比如：str="11" , length = 5 , symbol="0" , 最后返回 : 11000
     * @author zhangyd-c
     * @date 2015年9月8日 上午10:57:03
     * @param str 指定字符串
     * @param length 自定的字符串长度
     * @param symbol 补填的字符串
     * @return
     */
    public static String appendCharacterForLast(String str, int length, String symbol) {
        int strLen = str.length();
        if (strLen < length) {
            while (strLen < length) {
                StringBuffer sb = new StringBuffer();
                sb.append(str).append(symbol);// 左补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }
    
    /**
     * @Description 获取uuid
     * @author zhangyd-c
     * @date 2015年9月8日 上午11:22:57
     * @param isFormt 是否需要格式化。<br>
     *            此处格式化指：是否将uuid中的"-"去掉（true:格式化,false:不需要格式化）
     * @return
     */
    public static String getUUID(boolean isFormt) {
        String uuid = UUID.randomUUID().toString();
        if (isFormt) {
            uuid = uuid.replaceAll("-", "");
        }
        return uuid;
    }
    
    /**
     * @Description 首字母大写
     * @author zhangyd
     * @date 2015年12月18日 下午5:09:30 
     * @param str : xx_xx
     * @return
     */
	public static String initialtoUpper(String str){
		if(null != str)
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		else
			return str;
	}
	
	 /**
     * @Description 首字母大写
     * @author zhangyd
     * @date 2015年12月18日 下午5:09:30 
     * @param str : xx_xx
     * @return
     */
	public static String splitAndtoUpper(String str, String splitStr){
		String[] temps = str.split(splitStr);
		str = temps[0];
		for(int i = 1; i < temps.length; i++){
			str += initialtoUpper(temps[i]);
		}
		return str;
	}
	
	 /**
     * @Description 首字母大写
     * @author zhangyd
     * @date 2015年12月18日 下午5:09:30 
     * @param str : xx_xx
     * @return
     */
	public static String splitAndInitialtoUpper(String str, String splitStr){
		str = splitAndtoUpper(str, splitStr);
		return initialtoUpper(str);
	}
}
