package com.shopping.wx.util;

import java.text.Collator;
import java.util.Locale;

/**
 * @ClassName CnCompareUtil
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/18
 **/
public class CnCompareUtil {
    private final static Comparable<String> CHINA_COMPARE = (Comparable<String>) Collator.getInstance(Locale.CHINA);
}
