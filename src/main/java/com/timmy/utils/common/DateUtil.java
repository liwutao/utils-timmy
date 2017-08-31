package com.timmy.utils.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Copyright @2015 海尔集团 All rights reserved.
 * 广科数字技术有限公司专有/保密源代码,未经许可禁止任何人通过任何渠道使用、修改源代码.
 *
 * 该类提供所有日期相关的工具方法
 * @author LIWT
 * @create 2015年12月3日上午10:20:41
 */
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

