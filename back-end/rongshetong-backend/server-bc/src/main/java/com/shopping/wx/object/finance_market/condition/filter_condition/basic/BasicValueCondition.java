package com.shopping.wx.object.finance_market.condition.filter_condition.basic;

import com.shopping.wx.model.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class BasicValueCondition<T> {

    public BasicValueCondition(T value) {
        this.value = value;
    }

    final private T value;

    protected Predicate build(Root<Product> root, CriteriaBuilder builder) {
        return builder.equal(root.get(getAttributeName()), value);
    }

    protected abstract String getAttributeName();
}
