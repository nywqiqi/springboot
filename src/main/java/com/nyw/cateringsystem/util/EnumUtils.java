package com.nyw.cateringsystem.util;

import lombok.Getter;
import lombok.Setter;

/**
 * 枚举工具类
 * @author 宁有为
 * @version 1.0.0
 * @className EnumUtils
 */
public class EnumUtils {


    /**
     * 判断字符串值是否属于指定枚举类（忽略大小写）
     * @param value     待校验的字符串值
     * @param enumClass 枚举类的Class对象
     * @return true-属于该枚举，false-不属于或value为空
     */
    public static <T extends Enum<T>> boolean isBelongTo(String value, Class<T> enumClass) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        for (T t : enumClass.getEnumConstants()) {
            if (t.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
