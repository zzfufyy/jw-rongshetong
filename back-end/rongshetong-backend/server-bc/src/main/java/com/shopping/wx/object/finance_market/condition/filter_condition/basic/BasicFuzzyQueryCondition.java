package com.shopping.wx.object.finance_market.condition.filter_condition.basic;

import com.shopping.wx.model.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;

public abstract class BasicFuzzyQueryCondition {

    protected Predicate like(Root<Product> root, CriteriaBuilder builder, String... values) {
        return builder.or(
                Arrays.stream(values)
                        .map((string) -> builder.like(
                                root.get(getAttributeName()), '%' + string + '%'
                        )).toArray(Predicate[]::new)
        );
    }

    protected Predicate notLike(Root<Product> root, CriteriaBuilder builder, String... values) {
        return builder.and(
                Arrays.stream(values)
                        .map((string) -> builder.notLike(
                                root.get(getAttributeName()), '%' + string + '%'
                        )).toArray(Predicate[]::new)
        );
    }

    protected abstract String getAttributeName();
}
