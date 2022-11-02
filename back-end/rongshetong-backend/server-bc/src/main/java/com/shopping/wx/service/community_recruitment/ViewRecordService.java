package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.ViewRecord;
import com.shopping.wx.pojo.dto.view_record.ViewRecordDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName ViewRecordService
 * @Description 浏览记录接口
 * @Author zyw
 * @Date 2022/4/2
 **/
public interface ViewRecordService extends CrudService<ViewRecord> {
    List<ViewRecordDTO> pagedByDistance(PagingParam<String> pagingParam);
}
