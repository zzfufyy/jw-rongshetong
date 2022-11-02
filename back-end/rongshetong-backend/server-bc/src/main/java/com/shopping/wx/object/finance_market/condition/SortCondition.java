package com.shopping.wx.object.finance_market.condition;

public enum SortCondition {
    // 默认排序，无排序
    DEFAULT(null, true),
    // 年利率，递增
    ANNUAL_INTEREST_RATE("airMin", true),
    // 贷款额度，递减
    LOANS_LIMIT("loanLimitMax",false);

    SortCondition(String orderField, boolean acs) {
        this.orderField = orderField;
        this.acs = acs;
    }

    public String getOrderField() {
        return orderField;
    }

    public boolean isAcs() {
        return acs;
    }

    private final String orderField;
    private final boolean acs;
}
