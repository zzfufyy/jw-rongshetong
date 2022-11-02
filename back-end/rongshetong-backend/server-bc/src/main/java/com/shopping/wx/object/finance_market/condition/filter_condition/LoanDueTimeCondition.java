package com.shopping.wx.object.finance_market.condition.filter_condition;

import com.shopping.wx.model.Product;
import com.shopping.wx.object.finance_market.condition.filter_condition.basic.BasicRangeCondition;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 贷款期限条件
 */
public class LoanDueTimeCondition extends FilterCondition<LoanDueTimeCondition.LoanDueTimeStrategy> {

    public interface LoanDueTimeStrategy extends FilterStrategy {
    }

    public LoanDueTimeCondition() {
        addStrategy(new LessThen1Year());
        addStrategy(new Between1_3Year());
        addStrategy(new Between3_5Year());
        addStrategy(new MoreThen5Year());
    }
}

abstract class BasicLoanDueTimeCondition extends BasicRangeCondition<Integer> {
    @Override
    protected String getMaxAttributeName() {
        return "timeLimitMax";
    }

    @Override
    protected String getMinAttributeName() {
        return "timeLimitMin";
    }
}


class LessThen1Year extends BasicLoanDueTimeCondition implements LoanDueTimeCondition.LoanDueTimeStrategy {

    @Override
    public Predicate apply(Root<Product> root, CriteriaBuilder builder) {
        return lessThan(root, builder, 1);
    }
}

class Between1_3Year extends BasicLoanDueTimeCondition implements LoanDueTimeCondition.LoanDueTimeStrategy {

    @Override
    public Predicate apply(Root<Product> root, CriteriaBuilder builder) {
        return between(root, builder, 1, 3);
    }
}

class Between3_5Year extends BasicLoanDueTimeCondition implements LoanDueTimeCondition.LoanDueTimeStrategy {

    @Override
    public Predicate apply(Root<Product> root, CriteriaBuilder builder) {
        return between(root, builder, 3, 5);
    }
}

class MoreThen5Year extends BasicLoanDueTimeCondition implements LoanDueTimeCondition.LoanDueTimeStrategy {

    @Override
    public Predicate apply(Root<Product> root, CriteriaBuilder builder) {
        return moreThan(root, builder, 5);
    }
}





