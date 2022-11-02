package com.shopping.wx.service.ryd.impl;

import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.Dkpj;
import com.shopping.wx.model.Dkts;
import com.shopping.wx.service.ryd.RydFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RydFeedbackServiceImpl implements RydFeedbackService {

    @Autowired
    DB db;

    /**
     * 提交投诉
     *
     * @param loanId        贷款id
     * @param bankId        银行id
     * @param content       投诉内容
     * @param contact       联系人
     * @param contactNumber 联系电话
     */
    @Override
    public void commitComplaint(String loanId, String bankId, String content, String contact, String contactNumber) {
        Dkts complaint = new Dkts();
        complaint.setDkid(loanId);
        complaint.setDkbankid(bankId);
        complaint.setTsnr(content);
        complaint.setLxr(contact);
        complaint.setLxdh(contactNumber);
        // current
        complaint.setTstime(new Date());

        db.insert(complaint);
    }

    /**
     * 提交评价
     *
     * @param loanId  贷款id
     * @param bankId  银行id
     * @param score   分数
     * @param content 评价内容
     */
    @Override
    public void commitFeedback(String loanId, String bankId, String score, String content) {
        Dkpj feedback = new Dkpj();

        feedback.setDkid(loanId);
        feedback.setDkbankid(bankId);
        feedback.setPf(score);
        feedback.setPjyj(content);
        feedback.setPjtime(new Date());

        db.insert(feedback);
    }

    /**
     * 查询投诉
     *
     * @param id 投诉id
     * @return 查询结果
     */
    @Override
    public JSONObject queryComplaint(Integer id) {
        JSONObject result = new JSONObject();
        result.put("data", db.selectById(id, Dkts.class));
        return result;
    }


    /**
     * 查询评价
     *
     * @param id 评价id
     * @return 查询结果
     */
    @Override
    public JSONObject queryFeedback(Integer id) {
        JSONObject result = new JSONObject();
        result.put("data", db.selectById(id, Dkpj.class));
        return result;
    }
}
