package com.shopping.wx.util;

import org.apache.logging.log4j.core.util.UuidUtil;
import org.junit.Test;

import java.util.UUID;

/**
 * @author ljy
 * @date 2022-03-14 11:22
 */
public class UUIDUtils {

    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    public static String timeBaseUUID() {
        return UuidUtil.getTimeBasedUuid().toString();
    }

    public static String timeBaseUUID32() {
        return UuidUtil.getTimeBasedUuid().toString().replaceAll("-", "");
    }

    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};
    /**
     * 产生8位Uuid  千万级重复率为0
     *
     * @param
     * @return java.lang.String
     */
    public static String gen8CharUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }
}
