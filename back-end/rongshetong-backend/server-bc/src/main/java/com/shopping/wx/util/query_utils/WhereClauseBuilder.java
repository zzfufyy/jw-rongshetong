package com.shopping.wx.util.query_utils;

import com.shopping.wx.pojo.vo.basic.TimeCondition;
import com.shopping.wx.util.query_utils.impl.OperationImpl;
import groovy.lang.Tuple2;
import tk.mybatis.mapper.weekend.Fn;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.function.Consumer;

/**
 * @author ljy
 * @date 2022-03-16 17:04
 */
public class WhereClauseBuilder<T> {

    public interface Operation<O> {
        boolean predicate(O value);

        <Type> void handle(O value, Fn<Type, Object> fn, WeekendSqls<Type> sqls);
    }

    private final WeekendSqls<T> sqls;


    public WhereClauseBuilder() {
        this.sqls = WeekendSqls.custom();
    }

    public WhereClauseBuilder(WeekendSqls<T> sqls) {
        this.sqls = sqls;
    }

    public <O> WhereClauseBuilder<T> handle(O value, Fn<T, Object> fn, Operation<O> operation) {
        if (operation.predicate(value)) {
            operation.handle(value, fn, sqls);
        }
        return this;
    }

    public WhereClauseBuilder<T> notEmptyEq(String value, Fn<T, Object> fn) {
        return handle(value, fn, OperationImpl.NOT_EMPTY_EQ);
    }

    public WhereClauseBuilder<T> notEmptyNotEq(String value, Fn<T, Object> fn) {
        return handle(value, fn, OperationImpl.NOT_EMPTY_NOT_EQ);
    }

    public WhereClauseBuilder<T> notNullEq(Object value, Fn<T, Object> fn) {
        return handle(value, fn, OperationImpl.NOT_NULL_EQ);
    }

    public WhereClauseBuilder<T> notNullNotEq(Object value, Fn<T, Object> fn) {
        return handle(value, fn, OperationImpl.NOT_NULL_NOT_EQ);
    }

    public WhereClauseBuilder<T> notEmptyLike(String value, Fn<T, Object> fn) {
        return handle(value, fn, OperationImpl.NOT_EMPTY_LIKE);
    }

    public WhereClauseBuilder<T> dateRange(TimeCondition timeCondition, Fn<T, Object> fn) {
        // 大于等于开始时间
        notNullGtEq(timeCondition.getBeginTime(), fn);
        // 小于等于结束时间
        notNullLtEq(timeCondition.getEndTime(), fn);
        return this;
    }

    // 大于
    public WhereClauseBuilder<T> notNullGt(Object value, Fn<T, Object> fn) {
        return handle(value, fn, OperationImpl.NOT_NULL_GT);
    }

    // 大于等于
    public WhereClauseBuilder<T> notNullGtEq(Object value, Fn<T, Object> fn) {
        return handle(value, fn, OperationImpl.NOT_NULL_GT_EQ);
    }

    // 小于
    public WhereClauseBuilder<T> notNullLt(Object value, Fn<T, Object> fn) {
        return handle(value, fn, OperationImpl.NOT_NULL_LT);
    }

    // 小于等于
    public WhereClauseBuilder<T> notNullLtEq(Object value, Fn<T, Object> fn) {
        return handle(value, fn, OperationImpl.NOT_NULL_LT_EQ);
    }

    // in 范围
    public WhereClauseBuilder<T> notNullIn(Iterable<Object> values, Fn<T, Object> fn) {
        return handle(values, fn, OperationImpl.NOT_NULL_IN);
    }

    // 未删除
    public WhereClauseBuilder<T> notDeleted() {
        return handle(null, null, OperationImpl.NOT_DELETED);
    }

    // between
    public WhereClauseBuilder<T> notNullIn(Object begin, Object end, Fn<T, Object> fn) {
        return handle(new Tuple2<>(begin, end), fn, OperationImpl.NOT_NULL_BETWEEN);
    }

    // 是 Null
    public WhereClauseBuilder<T> isNull(Fn<T, Object> fn) {
        return handle(null, fn, OperationImpl.IS_NULL);
    }

    // 不是 null
    public WhereClauseBuilder<T> isNotNull(Fn<T, Object> fn) {
        return handle(null, fn, OperationImpl.IS_NOT_NULL);
    }

    // 原始查询
    public WhereClauseBuilder<T> raw(Consumer<WeekendSqls<T>> consumer) {
        if (consumer != null) {
            consumer.accept(sqls);
        }
        return this;
    }

    public WeekendSqls<T> getSqls() {
        return sqls;
    }


}
