package com.timmy.utils.common;

import net.sf.json.JSONNull;

public class StrUtil {

    public static boolean isEmpty(Object str) {
        if (str instanceof JSONNull) {
            return true;
        }
        return str == null || str.toString().trim().length() == 0;
    }
    
    public static boolean equals(String str1, String str2) {
        if (null == str1) {
            return null == str2;
        } else {
            return str1.equals(str2);
        }
    }
    
    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (null == str1) {
            return null == str2;
        } else if (null == str2) {
            return false;
        } else {
            return str1.toLowerCase().equals(str2.toLowerCase());
        }
    }
    
    public static boolean isNum(Object str) {
        if (isEmpty(str)) {
            return false;
        }
        try {
            Integer.parseInt(str.toString());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}
