package com.shopping.wx.service.basic;

import com.shopping.wx.conf.Page;

import java.util.List;

/**
 * @param <T> 目标实体类
 * @author ljy
 * @date 2022-03-11 11:06
 * <p>
 * 通用的增删查改服务
 * TODO： 检查数据是否存在
 */
public interface CrudService<T> {

    T selectById(Object id);

    T selectOne(T record);

    List<T> selectAll();

    List<T> select(T record);

    List<T> selectPage(Page page);

    List<T> selectAllByExample(Object example);

    List<T> selectPageByExample(Object example, Page page);

    long selectCountByExample(Object example);

    long selectCount(T record);

    int insert(T record);

    int update(T record);

    boolean logicDeleteById(Object id);

    boolean logicDelete(T record);

    int delete(T record);

    int deleteById(Object id);
}
