package com.shopping.wx.service.ryd.impl;

import com.shopping.wx.conf.DB;
import com.shopping.wx.conf.Page;
import com.shopping.wx.model.Bankqx;
import com.shopping.wx.model.Dksq;
import com.shopping.wx.model.Rwjd;
import com.shopping.wx.service.ryd.RydMyLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class RydMyLoanServiceImpl implements RydMyLoanService {

    @Autowired
    DB db;

    @Override
    public List<Bankqx> queryAuthorizationByOpenId(String openId) {
        Example example = new Example(Bankqx.class);
        // 根据表中的字段名称，这里是 oppenid
        example
                .createCriteria()
                .andEqualTo("oppenid", openId)
                // VARCHAR 权限类别:1-金融办权限  2-银行权限,
                // DOUBT: 这里必须传入数字 1 才有效，但是数据库里是 VARCHAR 类型
                .andEqualTo("type", 1);

        return db.selectAllByExample(Bankqx.class, example);
    }

    @Override
    public List<Dksq> queryAllLoansPaged(Page page) {
        return db.selectPage(Dksq.class, page);
    }

    @Override
    public List<Rwjd> queryTaskProgress(String loanId) {
        Example taskEx = new Example(Rwjd.class);
        taskEx
                .createCriteria()
                .andEqualTo("rwid", loanId);
        return db.selectAllByExample(Rwjd.class, taskEx);
    }
}
