package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.ResidentForPerson;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName ResidentForPersonService
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/27
 **/
public interface ResidentForPersonService extends CrudService<ResidentForPerson> {
    List<ResidentForPerson> listByResidentUuid(String residentUuid, Integer personType);

    int deleteByCellUuid(String cellUuid);
}
