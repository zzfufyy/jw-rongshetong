package com.shopping.wx.service.basic.impl;


import com.shopping.wx.conf.DB;
import com.shopping.wx.conf.Page;
import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.util.ReflectionUtils;
import com.shopping.wx.util.query_utils.Paging;
import com.shopping.wx.util.query_utils.PagingHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;

import java.lang.reflect.ParameterizedType;
import java.util.List;


/**
 * @author ljy
 * @date 2022-03-11 11:06
 */
@Slf4j
public abstract class CrudServiceImpl<T> implements CrudService<T>, PagingHelper<T> {
    /* ----------- Fields ----------- */
    /**
     * 注入的 DB 对象，即通用 Mapper 的包装类
     */
    @Autowired
    protected DB db;

    /**
     * 目标实体的泛型类
     */
    protected final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    protected CrudServiceImpl() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }


    /* ----------- APIs ----------- */

    @Override
    public T selectById(Object id) {
        return db.selectById(id, entityClass);
    }

    @Override
    public T selectOne(T record) {
        return db.selectOne(record);
    }

    @Override
    public List<T> selectAll() {
        return db.selectAll(entityClass);
    }

    @Override
    public List<T> select(T record) {//TODO TEST
        return db.select(record);
    }

    @Override
    public List<T> selectPage(Page page) {
        return db.selectPage(entityClass, page);
    }

    @Override
    public List<T> selectAllByExample(Object example) {
        return db.selectAllByExample(entityClass, example);
    }

    @Override
    public List<T> selectPageByExample(Object example, Page page) {
        return db.selectPageByExample(entityClass, example, page);
    }

    @Override
    public long selectCountByExample(Object example) {
        return db.selectCountByExample(entityClass, example);
    }

    @Override
    public long selectCount(T record) {
        return db.selectCount(record);
    }

    @Override
    //@Transactional(rollbackFor = Throwable.class)
    public int insert(T record) {
        return db.insert(record);
    }

    @Override
    //@Transactional(rollbackFor = Throwable.class)
    public int update(T record) {
        return db.update(record);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean logicDeleteById(Object id) {
        T record = selectById(id);
        if (record == null) {
            log.error("The target record with id[{}] is not existed", id);
            return false;
        }
        return logicDelete(record);
    }

    @Override
    public boolean logicDelete(T record) {
        Assert.notNull(record, "Must not be null");

        // TODO: 最好使用一个 基类 来提供审计字段，而不是采用反射获取
        boolean result = ReflectionUtils.setField(record, AuditConstant.STATUS_FIELD_NAME, AuditConstant.RecordStatus.DELETED.value());
        // 逻辑删除失败，检查日志
        if (!result) {
            return false;
        }

        update(record);
        return true;
    }

    @Override
    //@Transactional(rollbackFor = Throwable.class)
    public int delete(T record) {
        return db.delete(record);
    }

    @Override
    //@Transactional(rollbackFor = Throwable.class)
    public int deleteById(Object id) {
        return db.deleteById(entityClass, id);
    }

    /* ----------- Helper Methods ----------- */

    /**
     * @return 当前的实体类
     */
    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * 以 builder 的方式构建 example
     *
     * @return Builder
     */
    protected Example.Builder queryBuilder() {
        return Weekend.builder(entityClass);
    }

    /**
     * 要求调用者提供 {@link Paging} 来完成构造查询条件
     *
     * @param paging 查询构造实现
     * @return 查询结果
     */
    @Override
    public List<T> paging(Page page, Paging<T> paging) {
        return selectPageByExample(
                paging.build(entityClass),
                page
        );
    }
}
