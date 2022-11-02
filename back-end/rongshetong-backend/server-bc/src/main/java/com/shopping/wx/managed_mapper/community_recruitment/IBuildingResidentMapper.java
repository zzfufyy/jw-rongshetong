package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.model.BuildingResident;
import com.shopping.wx.pojo.dto.building_resident.BuildingResidentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface IBuildingResidentMapper {


    int insertByList(@Param("listBuildingResident") List<BuildingResident> listBuildingResident);


    BuildingResidentDTO infoWithAssociated(@Param("id") String id);

    void deleteByBuildingAndLayerList(
            @Param("buildingUuidList") List<String>buildingUuidList,
            @Param("layerList") List<Integer> layerList
    );

    void deleteByBuildingAndLayerFamilyList(
            @Param("buildingUuidList") List<String>buildingUuidList,
            @Param("layerFamilyList") List<Integer> layerFamilyList
    );


}
