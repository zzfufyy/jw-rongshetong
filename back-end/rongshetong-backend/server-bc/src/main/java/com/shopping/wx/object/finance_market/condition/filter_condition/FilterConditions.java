package com.shopping.wx.object.finance_market.condition.filter_condition;

public class FilterConditions {

    static public FilterConditions getInstance() {
        return Singleton.INSTANCE.instance;
    }

    public LoanGuarantyTypeCondition getLoanGuarantyTypeCondition() {
        return loanGuarantyTypeCondition;
    }

    private enum Singleton {
        /**
         * 单例模式
         */
        INSTANCE;

        Singleton() {
            this.instance = new FilterConditions();
        }

        public FilterConditions getInstance() {
            return instance;
        }

        private final FilterConditions instance;
    }

    private final LoanLimitCondition loanLimitCondition;
    private final LoanDueTimeCondition loanDueTimeCondition;
    private final TargetUserCondition targetUserCondition;
    private final LoanGuarantyTypeCondition loanGuarantyTypeCondition;
    private final FinanceOrganizationCondition financeOrganizationCondition;

    public LoanLimitCondition getLoanLimitCondition() {
        return loanLimitCondition;
    }

    public LoanDueTimeCondition getLoanDueTimeCondition() {
        return loanDueTimeCondition;
    }

    public TargetUserCondition getTargetUserCondition() {
        return targetUserCondition;
    }

    public FinanceOrganizationCondition getFinanceOrganizationCondition() {
        return financeOrganizationCondition;
    }

    private FilterConditions() {
        loanLimitCondition = new LoanLimitCondition();
        loanDueTimeCondition = new LoanDueTimeCondition();
        targetUserCondition = new TargetUserCondition();
        financeOrganizationCondition = new FinanceOrganizationCondition();
        loanGuarantyTypeCondition = new LoanGuarantyTypeCondition();
    }
}
