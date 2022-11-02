package com.shopping.wx.object.finance_market.condition.filter_condition.basic;

import com.shopping.wx.model.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class BasicRangeCondition<V extends Comparable<V>> {

    /**
     * 对于小于限定，取 最小值 小于右区间，
     */
    protected Predicate lessThan(Root<Product> root, CriteriaBuilder builder, V value) {
        return builder.lessThanOrEqualTo(root.get(getMinAttributeName()), value);
    }

    /**
     * 对于范围限定，取 最小值大于左区间 且 最大值小于右区间
     */
    protected Predicate between(Root<Product> root, CriteriaBuilder builder, V left, V right) {
        return builder.and(
                builder.lessThanOrEqualTo(root.get(getMinAttributeName()), left),
                builder.greaterThanOrEqualTo(root.get(getMaxAttributeName()), right)
        );
    }

    /**
     * 对于小于限定，取 最大值 大于左区间，
     */
    protected Predicate moreThan(Root<Product> root, CriteriaBuilder builder, V value) {
        return builder.greaterThanOrEqualTo(root.get(getMaxAttributeName()), value);
    }

    protected abstract String getMinAttributeName();

    protected abstract String getMaxAttributeName();
}