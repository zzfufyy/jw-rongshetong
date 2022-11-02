package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.DimHouseUsage;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName DimHouseUsageService
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/17
 **/
public interface DimHouseUsageService extends CrudService<DimHouseUsage> {
    List<DimHouseUsage> list();
}
