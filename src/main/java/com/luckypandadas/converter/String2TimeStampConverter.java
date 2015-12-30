/***********************************************************
 *将页面中的String转换为对应大java.sql.Timestamp
 *
 *@see(与该类相关联的类)
 *
 *广联达
 *
 *版权：本文件版权归属北京广联达软件股份有限公司
 *
 *
 *@author:xuefz
 *
 *@since:jdk1.5
 *
 *
 *@version:1.0
 *
 *@date:2014-4-22
 *
 *最后更改日期：
 *
 *
 *修改人：
 *
 *
 ********************************************************/

package com.luckypandadas.converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
/**
 *
 * @author (作者) zhangyd-c 
 * 2015年7月31日 下午5:53:04 
 * @version (版本) V1.0
 * @since (该版本支持的JDK版本) ： 1.7
 */
public class String2TimeStampConverter implements Converter<String, Timestamp> {
    
    public Timestamp convert(String source) {
        if (source == null || source.trim().length() < 1) {
            return null;
        } else if (source != null && source.trim().length() > 10) {
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateTimeFormat.setLenient(false);
            Date date;
            try {
                date = dateTimeFormat.parse(source);
                return new Timestamp(date.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            try {
                Date date = dateFormat.parse(source);
                return new Timestamp(date.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        
        return null;
    }
    
}
