package com.code.monitor.core.util;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author codedorado
 * @date 2021/01/26
 */
public class ArrayUtil {

    /**
     * 去掉数组中空项
     *
     * @param strings 数组
     * @return news
     */
    public static String[] trim(String[] strings) {
        return (strings == null || strings.length == 0) ? strings : Arrays.stream(strings)
                .filter(str -> Objects.nonNull(str) && str.length() > 0)
                .collect(Collectors.toList()).toArray(new String[]{});
    }

    /**
     * 匹配字符出现次数
     *
     * @param srcText  源字符串
     * @param findText 目标字符串
     * @return 出现次数
     */
    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }

}
