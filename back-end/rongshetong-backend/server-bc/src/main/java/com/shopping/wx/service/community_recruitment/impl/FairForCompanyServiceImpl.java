package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.managed_mapper.community_recruitment.IFairForCompanyMapper;
import com.shopping.wx.mapper.FairForCompanyMapper;
import com.shopping.wx.model.FairForCompany;
import com.shopping.wx.pojo.dto.fair_for_company.FairForCompanyDTO;
import com.shopping.wx.pojo.dto.fair_for_company.FairForCompanyJobDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.fair_for_company.FairForCompanySearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.FairForCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName FairForCompanyServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/23
 **/
@Service
public class FairForCompanyServiceImpl extends CrudServiceImpl<FairForCompany> implements FairForCompanyService {
    @Autowired
    private FairForCompanyMapper fairForCompanyMapper;
    @Autowired
    private IFairForCompanyMapper iFairForCompanyMapper;
    @Override
    public List<FairForCompanyDTO> pageDTO(PagingParam<FairForCompanySearchCondition> pagingParam) {
        FairForCompanySearchCondition condition = Optional
                .ofNullable(pagingParam.getCondition())
                .orElseGet(() -> new FairForCompanySearchCondition());
        startPage(pagingParam.getPage());
        return iFairForCompanyMapper.pageDTO(condition.getFairUuid());
    }

    @Override
    public List<FairForCompanyJobDTO> pageJobDTO(PagingParam<FairForCompanySearchCondition> pagingParam) {
        FairForCompanySearchCondition condition = Optional
                .ofNullable(pagingParam.getCondition())
                .orElseGet(() -> new FairForCompanySearchCondition());
        startPage(pagingParam.getPage());
        return iFairForCompanyMapper.pageJobDTO(condition.getFairUuid(),condition.getCompanyName());
    }
}
