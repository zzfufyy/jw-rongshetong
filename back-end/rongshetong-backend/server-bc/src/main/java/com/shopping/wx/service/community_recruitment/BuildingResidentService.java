package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.BuildingResident;
import com.shopping.wx.model.CommunityCellBuilding;
import com.shopping.wx.pojo.dto.building_resident.BuildingResidentDTO;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName BuildingResidentService
 * @Description 居民信息接口
 * @Author zyw
 * @Date 2022/4/11
 **/
public interface BuildingResidentService extends CrudService<BuildingResident> {

    /**
     * 根据楼栋信息 构建居民空表
     *
     * @param communityCellBuildingList
     * @return int
     */
    int insertListByCommunityCellBuilding(List<CommunityCellBuilding> communityCellBuildingList);

    /**
     * 根据建筑id 导出居民列表
     *
     * @param buildingUuid
     * @return java.util.List<com.shopping.wx.model.BuildingResident>
     */
    List<BuildingResident> loadListByBuildingUuid(String buildingUuid);

    /**
     * 返回  关联丰富后的  居户全表信息
     *
     * @param id
     * @return com.shopping.wx.pojo.dto.building_resident.BuildingResidentDTO
     */
    BuildingResidentDTO infoWithAssociated(String id);


    int updateByEntity(BuildingResident buildingResident);


    void updateByCompareLayerAndLayerFamily(String communityUuid,String cellUuid, int newNumLayer, int oldNumLayer, int newNumLayerFamily, int oldNumLayerFamily);



}
