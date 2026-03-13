package com.nexusops.common.core;

import lombok.experimental.UtilityClass;

/**
 * 字符串工具类
 */
@UtilityClass
public class StringUtils {

    /**
     * 判断字符串是否为空
     */
    public boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 判断字符串是否不为空
     */
    public boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
