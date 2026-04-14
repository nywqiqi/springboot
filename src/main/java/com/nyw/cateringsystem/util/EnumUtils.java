package com.nyw.cateringsystem.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className EnumUtils
 */
public class EnumUtils {

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
