package com.shopping.wx.util.query_utils;

import com.github.pagehelper.PageHelper;
import com.shopping.wx.conf.Page;
import com.shopping.wx.util.query_utils.impl.BuilderPaging;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author ljy
 * @date 2022-03-17 10:04
 */
public interface PagingHelper<T> {

    /**
     * 要求调用者提供 {@link Paging} 来完成构造查询条件
     *
     * @param paging 查询构造实现
     * @return 查询结果
     */
    List<T> paging(Page page, Paging<T> paging);


    /**
     * 最为基础的分页查询，要求调用者提供 sql 语句的 where sql 子句构造
     *
     * @param page  分页信息
     * @param where 构造 where sql 子语句
     * @return 查询结果
     */
    default List<T> pagingByWhereClause(Page page, Consumer<WeekendSqls<T>> where) {
        return paging(
                page,
                new BuilderPaging<>((builder) -> {
                    WeekendSqls<T> weekendSqls = WeekendSqls.custom();
                    where.accept(weekendSqls);
                    builder.andWhere(weekendSqls);
                })
        );
    }

    /**
     * 最为基础的分页查询，要求调用者提供 sql 语句的 where sql 子句构造
     *
     * @param page     分页信息
     * @param consumer 消耗构造 where sql 子语句的 builder
     * @return 查询结果
     */
    default List<T> pagingByWhereClauseBuilder(Page page, Consumer<WhereClauseBuilder<T>> consumer) {
        return paging(
                page,
                new BuilderPaging<>((builder) -> {
                    WeekendSqls<T> weekendSqls = WeekendSqls.custom();
                    WhereClauseBuilder<T> wcb = new WhereClauseBuilder<>(weekendSqls);
                    consumer.accept(wcb);
                    builder.andWhere(weekendSqls);
                })
        );
    }


    /**
     * 使用 Builder 构造 sql 分页查询，要求调用者提供 sql 语句的构造
     *
     * @param page     分页信息
     * @param consumer 构造 sql 语句
     * @return 查询结果
     */
    default List<T> pagingByBuilder(Page page, Consumer<Example.Builder> consumer) {
        return paging(page, new BuilderPaging<>(consumer));
    }


    default WeekendSqls<T> sql(Consumer<WeekendSqls<T>> consumer) {
        return new QueryUtils.SqlBuilder<>(consumer).build();
    }

    /**
     * 对下一次查询运用分页查询，用于手动实现分页查询
     *
     * @param page 分页条件
     */
    default void startPage(Page page) {
        if (Page.validate(page)) {
            PageHelper.startPage(page.getPage(), page.getRows());
        }
    }

    /**
     * 清除已经存在的 分页指令，避免不安全的分页
     */
    default void clearPage() {
        PageHelper.clearPage();
    }
}
