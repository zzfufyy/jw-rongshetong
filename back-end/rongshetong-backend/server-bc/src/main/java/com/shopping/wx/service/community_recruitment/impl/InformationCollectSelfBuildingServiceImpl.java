package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.mapper.InformationCollectSelfBuildingMapper;
import com.shopping.wx.model.InformationCollectSelfBuilding;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.InformationCollectSelfBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName InformationCollectSelfBuildingServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/5/10
 **/
@Service
public class InformationCollectSelfBuildingServiceImpl  extends CrudServiceImpl<InformationCollectSelfBuilding> implements InformationCollectSelfBuildingService {
    @Autowired
    InformationCollectSelfBuildingMapper informationCollectSelfBuildingMapper;

}
