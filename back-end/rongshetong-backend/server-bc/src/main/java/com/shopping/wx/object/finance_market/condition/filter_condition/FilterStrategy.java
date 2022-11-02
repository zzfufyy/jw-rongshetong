package com.shopping.wx.object.finance_market.condition.filter_condition;

import com.shopping.wx.model.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface FilterStrategy {
    /**
     * 返回一个可以描述当前过滤的 Predicate
     */
    Predicate apply(Root<Product> root, CriteriaBuilder builder);
}
