package com.timmy.utils.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
public final class DateUtil {
    
    /**
     * 该方法根据用户传入的日期格式返回根据该日期格式格式化的当前时间字符串
     * 
     * @param format 日期格式
     * @return
     */
    public static String getCurrentDateStr(String format) {
        
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(calendar.getTime());
    }
}

