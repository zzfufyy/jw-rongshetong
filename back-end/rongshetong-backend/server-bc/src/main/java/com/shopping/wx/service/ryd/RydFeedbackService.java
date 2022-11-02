package com.shopping.wx.service.ryd;

import com.alibaba.fastjson.JSONObject;
import com.shopping.base.foundation.result.ActionResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface RydFeedbackService {

    /**
     * 提交投诉
     *
     * @param loanId        贷款id
     * @param bankId        银行id
     * @param content       投诉内容
     * @param contact       联系人
     * @param contactNumber 联系电话
     */
    void commitComplaint(
            String loanId,
            String bankId,
            String content,
            String contact,
            String contactNumber
    );

    /**
     * 提交评价
     *
     * @param loanId  贷款id
     * @param bankId  银行id
     * @param score   分数
     * @param content 评价内容
     */
    void commitFeedback(
            String loanId,
            String bankId,
            String score,
            String content
    );

    /**
     * 查询投诉
     * @param id 投诉id
     * @return 查询结果
     */
    JSONObject queryComplaint(Integer id);

    /**
     * 查询评价
     * @param id 评价id
     * @return 查询结果
     */
    JSONObject queryFeedback(Integer id);
}
