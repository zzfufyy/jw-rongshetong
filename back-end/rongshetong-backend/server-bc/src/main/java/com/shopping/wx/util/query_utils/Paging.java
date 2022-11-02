package com.shopping.wx.util.query_utils;

import tk.mybatis.mapper.entity.Example;

/**
 * @author ljy
 * @date 2022-03-14 13:48
 */
public interface Paging<T> {

    Example build(Class<T> klass);
}
