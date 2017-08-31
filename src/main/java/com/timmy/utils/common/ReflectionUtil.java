package com.timmy.utils.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright @2015 海尔集团 All rights reserved.
 * 广科数字技术有限公司专有/保密源代码,未经许可禁止任何人通过任何渠道使用、修改源代码.<br>
 *
 * 该工具类主要提供反射相关的基本能力
 * @author LIWT
 * @create 2015年12月23日下午2:54:36
 */
public class ReflectionUtil {

    /**
     * 该方法获取一个类型上面所有类属性对象
     * 
     * @param clazz 类
     * @return 属性列表
     */
    public static List<Field> getAllFieds(Class<?> clazz) {
        if (null == clazz) {
            return new ArrayList<Field>(0);
        }
        List<Field> allFields = new ArrayList<Field>();
        Field[] fields = clazz.getDeclaredFields();
        allFields.addAll(Arrays.asList(fields));
        // 需要追溯到最终父类
        if (clazz != Object.class) {
            Class<?> superClazz = clazz.getSuperclass();
            allFields.addAll(getAllFieds(superClazz));
        }
        return allFields;
    }

    public static List<Method> getMethodsByName(String methodName, Class<?> clazz) {
        List<Method> methods = getAllMethods(clazz);
        List<Method> specialMethods = new ArrayList<Method>();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                specialMethods.add(method);
            }
        }
        return specialMethods;
    }
    
    public static List<Method> getAllMethods(Class<?> clazz) {
        if (clazz == Object.class) {
            return new ArrayList<Method>(0);
        }
        List<Method> allMethods = new ArrayList<Method>();
        List<Method> methods = Arrays.asList(clazz.getDeclaredMethods());
        allMethods.addAll(methods);
        if (clazz != Object.class) {
            allMethods.addAll(getAllMethods(clazz.getSuperclass()));
        }
        return allMethods;
    }
}
