package com.shopping.wx.util.query_utils;

import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.pojo.vo.basic.TimeCondition;
import com.shopping.wx.util.query_utils.impl.BuilderPaging;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;
import tk.mybatis.mapper.weekend.Fn;
import tk.mybatis.mapper.weekend.WeekendSqls;
import tk.mybatis.mapper.weekend.reflection.Reflections;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author ljy
 * @date 2022-03-14 11:17
 */
public class QueryUtils {
    public static String wrapCnOrderBy( String orderField){
        return  "convert("+orderField+ " using gbk)";
    }

    public static void notDeleted(WeekendSqls<?> sql) {
        // status 字段 不等于 -1
        sql.andNotEqualTo(AuditConstant.STATUS_FIELD_NAME, AuditConstant.RecordStatus.DELETED.value());
    }

    public static void notDeleted(Example.Criteria criteria) {
        criteria.andNotEqualTo(AuditConstant.STATUS_FIELD_NAME, AuditConstant.RecordStatus.DELETED.value());
    }

    public static class SqlBuilder<T> {
        private Consumer<WeekendSqls<T>> consumer;
        private final WeekendSqls<T> weekendSqls = WeekendSqls.custom();

        public SqlBuilder(Consumer<WeekendSqls<T>> consumer) {
            this.consumer = consumer;
        }

        public SqlBuilder<T> consumer(Consumer<WeekendSqls<T>> consumer) {
            this.consumer = consumer;
            return this;
        }

        public SqlBuilder<T> notDeleted() {
            weekendSqls.andEqualTo(AuditConstant.STATUS_FIELD_NAME, AuditConstant.RecordStatus.DELETED.value());
            return this;
        }

        public WeekendSqls<T> build() {
            if (consumer != null) {
                consumer.accept(weekendSqls);
            }
            return weekendSqls;
        }
    }

    /**
     * Use {@link com.shopping.wx.service.basic.impl.CrudServiceImpl} instead
     */
    @Deprecated
    public static <T> Paging<T> onlyWhereClausePaging(Consumer<WeekendSqls<T>> consumer) {
        return new BuilderPaging<>(
                (builder) -> {
                    WeekendSqls<T> weekendSqls = WeekendSqls.custom();
                    consumer.accept(weekendSqls);
                    builder.andWhere(weekendSqls);
                }
        );
    }

    /**
     * TODO: 使用搜素引擎来实现模糊查询
     *
     * @param value 模糊查询模板
     * @return 结果
     */
    public static String fuzzyQuery(String value) {
        return '%' + value + '%';
    }

    /**
     * 检查一个值是否为 null 之后的模板调用
     * <p>{@code QueryUtils.notNull(value,v -> doSomethingWith(v)); } </p>
     * <p>等价于 </p>
     * <p>{@code if(value != null){ doSomethingWith(value);} } </p>
     * 这样在 value 无法直接获取时较为好用
     * <p>
     *
     * @param value    目标值
     * @param consumer 消费者
     * @param <T>      目标值类型
     */
    public static <T> void notNull(T value, Consumer<T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
    }


    public static void notEmpty(String value, Consumer<String> consumer) {
        if (StringUtil.isNotEmpty(value)) {
            consumer.accept(value);
        }
    }

    public static void notEmpty(Integer value, Consumer<Integer> consumer) {
        if (null != value) {
            consumer.accept(value);
        }
    }

    public static <T> void rangeNotNull(T begin, T end, BiConsumer<T, T> consumer) {
        if (begin != null && end != null) {
            consumer.accept(begin, end);
        }
    }

    public static <T> String getFieldName(Fn<T, Object> fn) {
        return Reflections.fnToFieldName(fn);
    }

    /**
     * 按照日期范围来查询
     *
     * @param sql           sql 构造器
     * @param timeCondition 时间条件
     * @param fn            字段
     * @param <T>           实体类型
     */
    public static <T> void buildDateRange(
            WeekendSqls<T> sql,
            TimeCondition timeCondition,
            Fn<T, Object> fn
    ) {
        // 开始时间
        notNull(timeCondition.getBeginTime(), v -> sql.andGreaterThanOrEqualTo(fn, v));
        // 结束时间
        notNull(timeCondition.getEndTime(), v -> sql.andLessThanOrEqualTo(fn, v));
    }

    public static <T> void buildDateRange(
            WeekendSqls<T> sql,
            Date beginDate,
            Date endDate,
            Fn<T, Object> fn
    ) {
        // 开始时间
        notNull(beginDate, v -> sql.andGreaterThanOrEqualTo(fn, v));
        // 结束时间
        notNull(endDate, v -> sql.andLessThanOrEqualTo(fn, v));
    }
}
