package com.timmy.utils.common;

import java.util.Collection;
import java.util.List;

public final class CollectionUtil {

    public static boolean isEmpty(Collection<?> collection) {
        return !(null != collection && collection.size() > 0);
    }
    
    /**
     * 从列表中截取子列表(包括起始和结束位置元素)，如果起始位置大于等于结束位置、或者列表大小小于起始位置、或者源列表为空，都返回空列表
     * <br>如果列表大小小于结束位置，则返回起始位置到列表结束作为子串
     * 
     * @param srcList 源列表
     * @param startIndex 起始位置
     * @param endIndex 结束位置
     * @return 子列表
     */
    public static List<?> subList(List<?> srcList, int startIndex, int endIndex) {
        if (startIndex < 0 || startIndex > endIndex) {
            return null;
        }
        if (isEmpty(srcList)) {
            return srcList;
        }
        if (srcList.size() < startIndex + 1) {
            return null;
        }
        if (srcList.size() < endIndex + 1) {
            return srcList.subList(startIndex, srcList.size());
        }
        return srcList.subList(startIndex, endIndex+1);
    }
}
