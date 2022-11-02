package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.managed_mapper.community_recruitment.IBuildingResidentMapper;
import com.shopping.wx.mapper.BuildingResidentMapper;
import com.shopping.wx.model.BuildingResident;
import com.shopping.wx.model.CommunityCellBuilding;
import com.shopping.wx.pojo.dto.building_resident.BuildingResidentDTO;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.BuildingResidentService;
import com.shopping.wx.util.UUIDUtils;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;

/**
 * @ClassName BuildingResidentServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/11
 **/
@Service
public class BuildingResidentServiceImpl extends CrudServiceImpl<BuildingResident> implements BuildingResidentService {
    @Autowired
    IBuildingResidentMapper iBuildingResidentMapper;
    @Autowired
    BuildingResidentMapper buildingResidentMapper;


    @Override
    public int insertListByCommunityCellBuilding(List<CommunityCellBuilding> communityCellBuildingList) {
        List<BuildingResident> buildingResidentList = new ArrayList<>();
        communityCellBuildingList.forEach(v -> {
            int numLayer = v.getNumLayer();
            int numLayerFamily = v.getNumLayerFamily();
            // 楼层
            for (int i = 0; i < numLayer; i++) {
                int whichLayer = i + 1;
                // 第几户
                for (int j = 0; j < numLayerFamily; j++) {
                    int whichLayerFamily = j + 1;
                    BuildingResident buildingResident = new BuildingResident();
                    buildingResident.setId(UUIDUtils.randomUUID());
                    buildingResident.setWhichLayer(whichLayer);
                    buildingResident.setWhichLayerFamily(whichLayerFamily);
                    buildingResident.setBuildingUuid(v.getId());
                    buildingResident.setCellUuid(v.getCellUuid());
                    buildingResident.setCommunityUuid(v.getCommunityUuid());
                    buildingResidentList.add(buildingResident);
                }
            }
        });
        return iBuildingResidentMapper.insertByList(buildingResidentList);
    }

    @Override
    public List<BuildingResident> loadListByBuildingUuid(String buildingUuid) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<BuildingResident>()
                        // 等于buildinguuid
                        .notEmptyEq(buildingUuid, BuildingResident::getBuildingUuid)
                        .notDeleted()
                        .getSqls()
        );
        return buildingResidentMapper.selectByExample(builder.build());
    }

    @Override
    public BuildingResidentDTO infoWithAssociated(String id) {
        return iBuildingResidentMapper.infoWithAssociated(id);
    }

    @Override
    public int updateByEntity(BuildingResident buildingResident) {
        return buildingResidentMapper.updateByPrimaryKeySelective(buildingResident);
    }

    @Override
    public void updateByCompareLayerAndLayerFamily(
            String communityUuid, String cellUuid,
            int newNumLayer, int oldNumLayer,
            int newNumLayerFamily, int oldNumLayerFamily) {
        int flagCompareNumLayer = Integer.compare(newNumLayer, oldNumLayer);
        int flagCompareNumLayerFamily = Integer.compare(newNumLayerFamily, oldNumLayerFamily);
        // 获取该小区所有楼栋的buildingUuid
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.selectDistinct(QueryUtils.getFieldName(BuildingResident::getBuildingUuid));
        builder.where(
                new WhereClauseBuilder<BuildingResident>()
                        .notEmptyEq(cellUuid, BuildingResident::getCellUuid)
                        .getSqls());
        List<String> buildingUuidList = buildingResidentMapper.selectByExample(builder.build()).stream().
                map(v -> v.getBuildingUuid()).
                collect(Collectors.toList());
        // 目标插入值
        List<BuildingResident> insertBuildingResidentList = new ArrayList<>();
        List<Integer> deleteLayerList = new ArrayList<>();
        List<Integer> deleteLayerFamilyList = new ArrayList<>();
        // 先处理层
        if (flagCompareNumLayer == 0) {
            // 不处理
        } else if (flagCompareNumLayer == 1) {
            // 插入
            insertBuildingResidentList.clear();
            buildingUuidList.forEach(buildingUuid -> {
                for (int i = oldNumLayer + 1; i <= newNumLayer; i++) {
                    for (int j = 1; j <= oldNumLayerFamily; j++) {
                        BuildingResident tempBuildingResident = new BuildingResident();
                        tempBuildingResident.setId(UUIDUtils.randomUUID());
                        tempBuildingResident.setCommunityUuid(communityUuid);
                        tempBuildingResident.setCellUuid(cellUuid);
                        tempBuildingResident.setBuildingUuid(buildingUuid);
                        tempBuildingResident.setWhichLayer(i);
                        tempBuildingResident.setWhichLayerFamily(j);
                        insertBuildingResidentList.add(tempBuildingResident);
                    }
                }
            });
            iBuildingResidentMapper.insertByList(insertBuildingResidentList);
        } else if (flagCompareNumLayer == -1) {
            deleteLayerList.clear();
            for (int i = newNumLayer + 1; i <= oldNumLayer; i++) {
                deleteLayerList.add(i);
            }
            iBuildingResidentMapper.deleteByBuildingAndLayerList(buildingUuidList, deleteLayerList);
        }
        // 处理户（以new为基准）
        if (flagCompareNumLayerFamily == 0) {
            // 不处理
        } else if (flagCompareNumLayerFamily == 1) {
            insertBuildingResidentList.clear();
            buildingUuidList.forEach(buildingUuid -> {
                for (int i = 1; i <= newNumLayer; i++) {
                    for (int j = oldNumLayerFamily + 1; j <= newNumLayerFamily; j++) {
                        BuildingResident tempBuildingResident = new BuildingResident();
                        tempBuildingResident.setId(UUIDUtils.randomUUID());
                        tempBuildingResident.setCommunityUuid(communityUuid);
                        tempBuildingResident.setCellUuid(cellUuid);
                        tempBuildingResident.setBuildingUuid(buildingUuid);
                        tempBuildingResident.setWhichLayer(i);
                        tempBuildingResident.setWhichLayerFamily(j);
                        insertBuildingResidentList.add(tempBuildingResident);
                    }
                }
            });
            iBuildingResidentMapper.insertByList(insertBuildingResidentList);
        } else if (flagCompareNumLayerFamily == -1) {
            deleteLayerFamilyList.clear();
            for (int i = newNumLayerFamily + 1; i <= oldNumLayer; i++) {
                deleteLayerFamilyList.add(i);
            }
            iBuildingResidentMapper.deleteByBuildingAndLayerFamilyList(buildingUuidList, deleteLayerFamilyList);
        }

    }

}
