package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.managed_mapper.community_recruitment.IViewRecordMapper;
import com.shopping.wx.model.ViewRecord;
import com.shopping.wx.pojo.dto.view_record.ViewRecordDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.ViewRecordService;
import com.shopping.wx.util.SalaryCompareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ViewRecordServiceImpl
 * @Description 浏览记录 接口实现类
 * @Author zyw
 * @Date 2022/4/2
 **/
@Service
public class ViewRecordServiceImpl extends CrudServiceImpl<ViewRecord> implements ViewRecordService {
    @Autowired
    IViewRecordMapper  iViewRecordMapper;

    @Override
    public List<ViewRecordDTO> pagedByDistance(PagingParam<String> pagingParam) {
        startPage(pagingParam.getPage());
        // 构建薪资比较模式
        return iViewRecordMapper.pagedByDistance(pagingParam.getCondition());
    }
}
