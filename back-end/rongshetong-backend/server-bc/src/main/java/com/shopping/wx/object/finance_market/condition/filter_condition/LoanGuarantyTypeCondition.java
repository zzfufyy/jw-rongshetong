package com.shopping.wx.object.finance_market.condition.filter_condition;

import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.model.Product;
import com.shopping.wx.object.finance_market.condition.filter_condition.basic.BasicFuzzyQueryCondition;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class LoanGuarantyTypeCondition extends FilterCondition<LoanGuarantyTypeCondition.LoanGuarantyTypeStrategy> {

    public interface LoanGuarantyTypeStrategy extends FilterStrategy {
        int SELECTED_TYPE = 0;
        int OTHER_TYPE = 1;
    }

    public LoanGuarantyTypeStrategy getStrategy(JSONObject object) {
        if (object == null) {
            return null;
        }

        switch (object.getInteger("type")) {
            case LoanGuarantyTypeStrategy.SELECTED_TYPE:
                return new LoanGuarantyLikeStrategy(object.getJSONArray("values").toArray(new String[0]));
            case LoanGuarantyTypeStrategy.OTHER_TYPE:
                return new LoanGuarantyNotLikeStrategy(object.getJSONArray("values").toArray(new String[0]));
            default:
                assert false;
                return null;
        }
    }
}

abstract class BasicGuarantyTypeCondition extends BasicFuzzyQueryCondition {

    @Override
    protected String getAttributeName() {
        return "ftype";
    }
}

class LoanGuarantyLikeStrategy extends BasicGuarantyTypeCondition implements LoanGuarantyTypeCondition.LoanGuarantyTypeStrategy {

    private final String[] values;

    public LoanGuarantyLikeStrategy(String[] values) {
        this.values = values;
    }

    @Override
    public Predicate apply(Root<Product> root, CriteriaBuilder builder) {
        return like(root, builder, values);
    }


}

class LoanGuarantyNotLikeStrategy extends BasicGuarantyTypeCondition implements LoanGuarantyTypeCondition.LoanGuarantyTypeStrategy {

    private final String[] values;

    public LoanGuarantyNotLikeStrategy(String[] values) {
        this.values = values;
    }


    @Override
    public Predicate apply(Root<Product> root, CriteriaBuilder builder) {
        return notLike(root, builder, values);
    }
}
