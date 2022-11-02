package com.shopping.wx.util.query_utils.impl;

import com.shopping.wx.util.query_utils.Paging;
import tk.mybatis.mapper.entity.Example;

/**
 * @author ljy
 * @date 2022-03-14 16:02
 */
public class CustomPaging<T> implements Paging<T> {


    @Override
    public Example build(Class<T> klass) {
        return null;
    }
}
