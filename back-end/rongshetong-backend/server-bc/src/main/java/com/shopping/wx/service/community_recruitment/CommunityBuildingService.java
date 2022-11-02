package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.CommunityBuilding;
import com.shopping.wx.pojo.dto.form_information.FormInformationPlus;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.community_building.CommunityBuildingSearchCondition;
import com.shopping.wx.pojo.vo.form_information.FormInformationSearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName CommunityBuildingService
 * @Description TODO
 * @Author zyw
 * @Date 2022/9/14
 **/
public interface CommunityBuildingService extends CrudService<CommunityBuilding> {

    List<CommunityBuilding> page(PagingParam<CommunityBuildingSearchCondition> pagingParam);
    List<CommunityBuilding> pageStreet(PagingParam<CommunityBuildingSearchCondition> pagingParam);
}
