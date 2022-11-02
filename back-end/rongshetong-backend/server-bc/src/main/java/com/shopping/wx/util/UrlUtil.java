package com.shopping.wx.util;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.util.StringUtil;

/**
 * @ClassName UrlUtil
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/26
 **/
@Slf4j
public class UrlUtil {
    public static String appendHeadSlash(String str){
        if(StringUtil.isNotEmpty(str)){
            String firstLetter = str.substring(0, 1);
            if(firstLetter != "/"){
                str = "/" +  str;
            }
            return str;
        }
        return str;
    }
}
