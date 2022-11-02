package com.shopping.wx.object.finance_market.condition.filter_condition;

import com.shopping.wx.model.Product;
import com.shopping.wx.object.finance_market.condition.filter_condition.basic.BasicValueCondition;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class FinanceOrganizationCondition extends FilterCondition<FinanceOrganizationCondition.FinanceOrganizationStrategy> {

    public interface FinanceOrganizationStrategy extends FilterStrategy {
    }

    @Override
    public FinanceOrganizationStrategy getStrategy(Integer index) {
        throw new NoSuchMethodError();
    }

    public FinanceOrganizationStrategy getStrategy(String bankId) {
        // 无筛选模式
        if (bankId == null) {
            return null;
        } else {
            // 通过给定的 organ id 进行创建
            return new FinanceOrganizationValueStrategy(bankId);
        }
    }
}

class FinanceOrganizationValueStrategy extends BasicValueCondition<String> implements FinanceOrganizationCondition.FinanceOrganizationStrategy {

    public FinanceOrganizationValueStrategy(String value) {
        super(value);
    }

    @Override
    protected String getAttributeName() {
        return "bankid";
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
