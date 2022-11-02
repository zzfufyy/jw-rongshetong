package com.shopping.wx.util;

import com.shopping.wx.constant.AuditConstant;
import lombok.Getter;

/**
 * @ClassName SalaryCompareUtil
 * @Description 薪资比较类
 * @Author zyw
 * @Date 2022/4/1
 **/

public class SalaryCompareUtil {


    @Getter
    Integer min;
    @Getter
    Integer max;

    private SalaryCompareUtil() {
    }

    public SalaryCompareUtil(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }


    public Integer getCompareMode() {
        if (this.min == null || this.max == null) {
            return -1;
        } else if (this.min == 0 && this.max == 0) {
            return AuditConstant.SalaryCompareState.NEOGIATION.getValue();
        } else if (this.min == 0 && this.max > 0) {
            return AuditConstant.SalaryCompareState.UNDERSALARY.getValue();
        } else if (this.min > 0 && this.max == 0) {
            return AuditConstant.SalaryCompareState.UPERSALARY.getValue();
        } else if (this.min > 0 && this.max > 0) {
            return AuditConstant.SalaryCompareState.BETWEENSALARY.getValue();
        }
        return null;
    }
}
