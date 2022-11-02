package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.CommunityCell;
import com.shopping.wx.model.CommunityCellBuilding;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName CommunityCellBuildingService
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/11
 **/
public interface CommunityCellBuildingService extends CrudService<CommunityCellBuilding> {

    /**
     * 根据小区信息  添加楼栋信息
     *
     * @param communityCell
     * @return 构建的楼栋信息
     */
    List<CommunityCellBuilding> insertListByCommunityCell(CommunityCell communityCell);


    List<CommunityCellBuilding> listByCellUuid(String cellUuid);
    /**
     * 根据社区id加载 楼栋实体列表
     *
     * @param communityUuid
     * @return
     */
    List<CommunityCellBuilding> listByCommunityUuid(String communityUuid);

    void updateByCompareNumBuilding( String cellUuid, int buildingNameType, int newNumBuilding, int oldNumBuilding);
}
