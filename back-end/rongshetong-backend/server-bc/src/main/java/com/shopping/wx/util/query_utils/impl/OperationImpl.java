package com.shopping.wx.util.query_utils.impl;

import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import groovy.lang.Tuple2;
import tk.mybatis.mapper.util.StringUtil;
import tk.mybatis.mapper.weekend.Fn;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.Objects;

/**
 * @author ljy
 * @date 2022-03-17 9:18
 * <p>
 * 定义操作实现常量
 */
public interface OperationImpl {
    WhereClauseBuilder.Operation<String> NOT_EMPTY_EQ = (EqOperation<String>) StringUtil::isNotEmpty;

    WhereClauseBuilder.Operation<String> NOT_EMPTY_NOT_EQ = (NotEqOperation<String>) StringUtil::isNotEmpty;

    WhereClauseBuilder.Operation<Object> NOT_NULL_EQ = (EqOperation<Object>) Objects::nonNull;

    WhereClauseBuilder.Operation<Object> NOT_NULL_NOT_EQ = (NotEqOperation<Object>) Objects::nonNull;

    WhereClauseBuilder.Operation<String> NOT_EMPTY_LIKE = (LikeOperation) StringUtil::isNotEmpty;

    WhereClauseBuilder.Operation<Object> NOT_NULL_GT = (GtOperation<Object>) Objects::nonNull;

    WhereClauseBuilder.Operation<Object> NOT_NULL_GT_EQ = (GtEqOperation<Object>) Objects::nonNull;

    WhereClauseBuilder.Operation<Object> NOT_NULL_LT = (LtOperation<Object>) Objects::nonNull;

    WhereClauseBuilder.Operation<Object> NOT_NULL_LT_EQ = (LtEqOperation<Object>) Objects::nonNull;

    WhereClauseBuilder.Operation<Iterable<Object>> NOT_NULL_IN = (InOperation<Object>) Objects::nonNull;

    WhereClauseBuilder.Operation<Tuple2<Object, Object>> NOT_NULL_BETWEEN =
            (BetweenOperation<Object>) value -> value.getFirst() != null && value.getSecond() != null;

    WhereClauseBuilder.Operation<Void> NOT_DELETED = new NotDeletedOperation();

    WhereClauseBuilder.Operation<Void> IS_NULL = new IsNullOperation();

    WhereClauseBuilder.Operation<Void> IS_NOT_NULL = new IsNotNullOperation();
}


/* -------------- Implementations ------------- */

interface EqOperation<O> extends WhereClauseBuilder.Operation<O> {
    @Override
    default <Type> void handle(O value, Fn<Type, Object> fn, WeekendSqls<Type> sqls) {
        sqls.andEqualTo(fn, value);
    }
}

interface GtOperation<O> extends WhereClauseBuilder.Operation<O> {
    @Override
    default <Type> void handle(O value, Fn<Type, Object> fn, WeekendSqls<Type> sqls) {
        sqls.andGreaterThan(fn, value);
    }
}

interface GtEqOperation<O> extends WhereClauseBuilder.Operation<O> {
    @Override
    default <Type> void handle(O value, Fn<Type, Object> fn, WeekendSqls<Type> sqls) {
        sqls.andGreaterThanOrEqualTo(fn, value);
    }
}

interface LtOperation<O> extends WhereClauseBuilder.Operation<O> {
    @Override
    default <Type> void handle(O value, Fn<Type, Object> fn, WeekendSqls<Type> sqls) {
        sqls.andLessThan(fn, value);
    }
}

interface LtEqOperation<O> extends WhereClauseBuilder.Operation<O> {
    @Override
    default <Type> void handle(O value, Fn<Type, Object> fn, WeekendSqls<Type> sqls) {
        sqls.andLessThanOrEqualTo(fn, value);
    }
}

interface LikeOperation extends WhereClauseBuilder.Operation<String> {
    @Override
    default <Type> void handle(String value, Fn<Type, Object> fn, WeekendSqls<Type> sqls) {
        sqls.andLike(fn, QueryUtils.fuzzyQuery(value));
    }
}

interface NotEqOperation<O> extends WhereClauseBuilder.Operation<O> {
    @Override
    default <Type> void handle(O value, Fn<Type, Object> fn, WeekendSqls<Type> sqls) {
        sqls.andNotEqualTo(fn, value);
    }
}

interface InOperation<O> extends WhereClauseBuilder.Operation<Iterable<O>> {
    @Override
    default <Type> void handle(Iterable<O> value, Fn<Type, Object> fn, WeekendSqls<Type> sqls) {
        sqls.andIn(fn, value);
    }
}

interface BetweenOperation<O> extends WhereClauseBuilder.Operation<Tuple2<O, O>> {
    @Override
    default <Type> void handle(Tuple2<O, O> value, Fn<Type, Object> fn, WeekendSqls<Type> sqls) {
        sqls.andBetween(fn, value.getFirst(), value.getSecond());
    }
}

final class NotDeletedOperation implements WhereClauseBuilder.Operation<Void> {

    @Override
    public boolean predicate(Void value) {
        return true;
    }

    @Override
    public <Type> void handle(Void value, Fn<Type, Object> fn, WeekendSqls<Type> sqls) {
        assert fn == null;
        sqls.andNotEqualTo(AuditConstant.STATUS_FIELD_NAME, AuditConstant.RecordStatus.DELETED.value());
    }
}

final class IsNullOperation implements WhereClauseBuilder.Operation<Void> {

    @Override
    public boolean predicate(Void value) {
        return true;
    }

    @Override
    public <Type> void handle(Void value, Fn<Type, Object> fn, WeekendSqls<Type> sqls) {
        sqls.andIsNull(fn);
    }
}

final class IsNotNullOperation implements WhereClauseBuilder.Operation<Void> {

    @Override
    public boolean predicate(Void value) {
        return true;
    }

    @Override
    public <Type> void handle(Void value, Fn<Type, Object> fn, WeekendSqls<Type> sqls) {
        sqls.andIsNotNull(fn);
    }
}




