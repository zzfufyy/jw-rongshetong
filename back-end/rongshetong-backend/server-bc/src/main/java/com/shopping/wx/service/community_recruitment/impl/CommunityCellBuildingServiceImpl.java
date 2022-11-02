package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.managed_mapper.community_recruitment.ICommunityCellBuildingMapper;
import com.shopping.wx.mapper.CommunityCellBuildingMapper;
import com.shopping.wx.model.CommunityCell;
import com.shopping.wx.model.CommunityCellBuilding;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.CommunityCellBuildingService;
import com.shopping.wx.util.UUIDUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import com.shopping.wx.util_service.BuildingNameListBuilder;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CommunityCellBuildingServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/11
 **/
@Service
public class CommunityCellBuildingServiceImpl extends CrudServiceImpl<CommunityCellBuilding> implements CommunityCellBuildingService {
    @Autowired
    ICommunityCellBuildingMapper iCommunityCellBuildingMapper;
    @Autowired
    CommunityCellBuildingMapper communityCellBuildingMapper;

    @Override
    public List<CommunityCellBuilding> insertListByCommunityCell(CommunityCell communityCell) {
        int numBuilding = communityCell.getNumBuilding();
        // 构建建筑名字清单
        BuildingNameListBuilder buildingNameListBuilder = new BuildingNameListBuilder(
                communityCell.getBuildingNameType(), numBuilding);
        List<String> buildingNameList = buildingNameListBuilder.build();

        List<CommunityCellBuilding> communityCellBuildingList = new ArrayList<>(numBuilding);
        // 根据numBuilding 建立循环构建 list
        for (int i = 0; i < numBuilding; i++) {
            CommunityCellBuilding currentCommunityCellBuilding = new CommunityCellBuilding();
            currentCommunityCellBuilding.setId(UUIDUtils.randomUUID());
            currentCommunityCellBuilding.setBuildingOrder(i + 1);
            currentCommunityCellBuilding.setBuildingName(buildingNameList.get(i));
            currentCommunityCellBuilding.setNumLayer(communityCell.getNumLayer());
            currentCommunityCellBuilding.setNumLayerFamily(communityCell.getNumLayerFamily());
            currentCommunityCellBuilding.setCellUuid(communityCell.getId());
            currentCommunityCellBuilding.setCommunityUuid(communityCell.getCommunityUuid());
            communityCellBuildingList.add(currentCommunityCellBuilding);
        }
        iCommunityCellBuildingMapper.insertByList(communityCellBuildingList);
        return communityCellBuildingList;
    }

    @Override
    public List<CommunityCellBuilding> listByCellUuid(String cellUuid) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<CommunityCellBuilding>()
                        .notEmptyEq(cellUuid, CommunityCellBuilding::getCellUuid)
                        .notDeleted()
                        .getSqls()
        );
        return communityCellBuildingMapper.selectByExample(builder.build());
    }

    @Override
    public List<CommunityCellBuilding> listByCommunityUuid(String communityUuid) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<CommunityCellBuilding>()
                        .notEmptyEq(communityUuid, CommunityCellBuilding::getCommunityUuid)
                        .notDeleted()
                        .getSqls()
        );
        return communityCellBuildingMapper.selectByExample(builder.build());
    }

    @Override
    public void updateByCompareNumBuilding(String cellUuid, int buildingNameType, int newNumBuilding, int oldNumBuilding) {
        switch (Integer.compare(newNumBuilding, oldNumBuilding)) {
            case 0:
                break;
            case -1:
                List<Integer> listBuildingOrder = new ArrayList<>();
                for (int i = newNumBuilding + 1; i <= oldNumBuilding; i++) {
                    listBuildingOrder.add(i);
                }
                iCommunityCellBuildingMapper.batchDeleteByBuildingOrderList(cellUuid, listBuildingOrder);
                break;
            case 1:
                CommunityCellBuilding queryCommunityCellBuilding = new CommunityCellBuilding();
                queryCommunityCellBuilding.setCellUuid(cellUuid);
                // 获取示例
                Example.Builder builder = new Example.Builder(getEntityClass());
                builder.where(
                        new WhereClauseBuilder<CommunityCellBuilding>()
                                .notEmptyEq(cellUuid, CommunityCellBuilding::getCellUuid)
                                .getSqls()
                );
                CommunityCellBuilding exampleCommunityCellBuilding = communityCellBuildingMapper.selectByExampleAndRowBounds(
                        builder.build(),
                        new RowBounds(0, 1)
                ).get(0);

                // 构建插入list
                List<CommunityCellBuilding> communityCellBuildingList = new ArrayList<>();
                for (int i = oldNumBuilding + 1; i <= newNumBuilding; i++) {
                    CommunityCellBuilding thisCommunityCellBuilding = new CommunityCellBuilding();
                    BeanUtils.copyProperties(exampleCommunityCellBuilding, thisCommunityCellBuilding);
                    thisCommunityCellBuilding.setId(UUIDUtils.randomUUID());
                    thisCommunityCellBuilding.setBuildingOrder(i);
                    thisCommunityCellBuilding.setBuildingName(BuildingNameListBuilder.buildBuildingName(
                            buildingNameType, i
                    ));
                    thisCommunityCellBuilding.setCreateTime(new Date());
                    communityCellBuildingList.add(thisCommunityCellBuilding);
                }
                iCommunityCellBuildingMapper.insertByList(communityCellBuildingList);
                break;
            default:
                break;
        }

    }
}
