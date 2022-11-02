package com.shopping.wx.object.finance_market.condition.filter_condition;

import com.shopping.wx.model.Product;
import com.shopping.wx.object.finance_market.condition.filter_condition.basic.BasicValueCondition;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TargetUserCondition extends FilterCondition<TargetUserCondition.TargetUserStrategy> {

    public interface TargetUserStrategy extends FilterStrategy {

    }

    @Override
    public TargetUserStrategy getStrategy(Integer index) {
        throw new NoSuchMethodError();
    }

    public TargetUserStrategy getStrategy(String userType) {
        // 无筛选模式
        if (userType == null) {
            return null;
        } else {
            return new TargetUserValueStrategy(userType);
        }
    }
}

class TargetUserValueStrategy extends BasicValueCondition<String> implements TargetUserCondition.TargetUserStrategy {

    public TargetUserValueStrategy(String value) {
        super(value);
    }

    @Override
    protected String getAttributeName() {
        return "floanUse";
    }

    /**
     * 返回一个可以描述当前过滤的 Predicate
     *
     * @param root
     * @param builder
     */
    @Override
    public Predicate apply(Root<Product> root, CriteriaBuilder builder) {
        return build(root, builder);
    }
}


