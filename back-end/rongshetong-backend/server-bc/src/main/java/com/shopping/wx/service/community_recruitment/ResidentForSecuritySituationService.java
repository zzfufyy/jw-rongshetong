package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.ResidentForSecuritySituation;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName DimHouseUsageService
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/17
 **/
public interface ResidentForSecuritySituationService extends CrudService<ResidentForSecuritySituation> {
    List<ResidentForSecuritySituation> listByResidentUuid(String residentUuid);

}
