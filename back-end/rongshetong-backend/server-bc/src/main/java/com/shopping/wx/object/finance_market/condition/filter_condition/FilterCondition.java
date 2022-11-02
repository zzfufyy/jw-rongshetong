package com.shopping.wx.object.finance_market.condition.filter_condition;

import java.util.ArrayList;
import java.util.List;

public abstract class FilterCondition<S extends FilterStrategy> {

    private final List<S> strategies;

    protected FilterCondition() {
        strategies = new ArrayList<>();
        // 默认的第一个无选项
        strategies.add(null);
    }

    protected void addStrategy(S strategy) {
        strategies.add(strategy);
    }

    public S getStrategy(Integer index) {
        if (index == null) {
            return null;
        }
        return strategies.get(index);
    }
}
