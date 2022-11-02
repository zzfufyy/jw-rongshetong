package com.shopping.wx.controller.ryd;


import com.alibaba.fastjson.JSONObject;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.service.ryd.RydFeedbackService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j
@RequestMapping("/ryd/LoanFeedback")
public class RydLoanFeedbackController {

    final
    RydFeedbackService rydFeedbackService;

    public RydLoanFeedbackController(RydFeedbackService rydFeedbackService) {
        this.rydFeedbackService = rydFeedbackService;
    }

    /**
     * 请求提交投诉
     *
     * @param loanId        贷款id
     * @param bankId        银行id
     * @param content       投诉内容
     * @param contact       联系人
     * @param contactNumber 联系电话
     * @return 请求结果
     */
    @RequestMapping(value = "/commitComplaint", method = RequestMethod.POST)
    ActionResult<Void> commitComplaint(
            String loanId,
            String bankId,
            String content,
            String contact,
            String contactNumber
    ) {
        rydFeedbackService.commitComplaint(
                loanId,
                bankId,
                content,
                contact,
                contactNumber
        );
        return ActionResult.ok();
    }


    /**
     * 请求提交评价
     *
     * @param loanId  贷款id
     * @param bankId  银行id
     * @param score   分数
     * @param content 评价内容
     * @return 请求结果
     */
    @RequestMapping(value = "/commitFeedback", method = RequestMethod.POST)
    ActionResult<Void> commitFeedback(
            String loanId,
            String bankId,
            String score,
            String content
    ) {
        rydFeedbackService.commitFeedback(
                loanId,
                bankId,
                score,
                content
        );
        return ActionResult.ok();
    }

    /**
     * 请求查询投诉
     *
     * @param id 投诉 id
     * @return 查询结果
     */
    @RequestMapping(value = "/queryComplaint", method = RequestMethod.GET)
    ActionResult<JSONObject> queryComplaint(Integer id) {
        return ActionResult.ok(rydFeedbackService.queryComplaint(id));
    }

    /**
     * 请求查询投诉
     *
     * @param id 投诉 id
     * @return 查询结果
     */
    @RequestMapping(value = "/queryFeedback", method = RequestMethod.GET)
    ActionResult<JSONObject> queryFeedback(Integer id) {
        return ActionResult.ok(rydFeedbackService.queryFeedback(id));
    }
}
