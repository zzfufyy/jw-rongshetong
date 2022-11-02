package com.shopping.wx.service.ryd;

import com.shopping.wx.conf.Page;
import com.shopping.wx.model.Bankqx;
import com.shopping.wx.model.Dksq;
import com.shopping.wx.model.Rwjd;

import java.util.List;

public interface RydMyLoanService {

    /**
     *
     * @param openId
     * @return
     */
    List<Bankqx> queryAuthorizationByOpenId(String openId);


    List<Dksq> queryAllLoansPaged(Page page);

    List<Rwjd> queryTaskProgress(String loanId);
}
