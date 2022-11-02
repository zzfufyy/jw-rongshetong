package com.shopping.wx.object.finance_market.condition.filter_condition;

import com.shopping.wx.model.Product;
import com.shopping.wx.object.finance_market.condition.filter_condition.basic.BasicRangeCondition;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class LoanLimitCondition extends FilterCondition<LoanLimitCondition.LoanLimitStrategy> {

    public interface LoanLimitStrategy extends FilterStrategy {
    }

    public LoanLimitCondition() {
        addStrategy(new LessThan30());
        addStrategy(new Between30_100());
        addStrategy(new Between100_300());
        addStrategy(new Between300_500());
        addStrategy(new MoreThen500());
    }
}

abstract class BasicLoanLimitCondition extends BasicRangeCondition<Integer> implements LoanLimitCondition.LoanLimitStrategy {
    @Override
    protected String getMinAttributeName() {
        return "loanLimitMin";
    }

    @Override
    protected String getMaxAttributeName() {
        return "loanLimitMax";
    }
}

class LessThan30 extends BasicLoanLimitCondition {

    @Override
    public Predicate apply(Root<Product> root, CriteriaBuilder builder) {
        return lessThan(root, builder, 30);
    }
}

class Between30_100 extends BasicLoanLimitCondition {
    @Override
    public Predicate apply(Root<Product> root, CriteriaBuilder builder) {
        return between(root, builder, 30, 100);
    }
}

class Between100_300 extends BasicLoanLimitCondition {
    @Override
    public Predicate apply(Root<Product> root, CriteriaBuilder builder) {
        return between(root, builder, 100, 300);
    }
}

class Between300_500 extends BasicLoanLimitCondition {
    @Override
    public Predicate apply(Root<Product> root, CriteriaBuilder builder) {
        return between(root, builder, 300, 500);
    }
}

class MoreThen500 extends BasicLoanLimitCondition {
    @Override
    public Predicate apply(Root<Product> root, CriteriaBuilder builder) {
        return moreThan(root, builder, 500);
    }
}
