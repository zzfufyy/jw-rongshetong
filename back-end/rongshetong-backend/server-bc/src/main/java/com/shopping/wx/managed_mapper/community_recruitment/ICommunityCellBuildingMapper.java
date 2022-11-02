package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.model.CommunityCellBuilding;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName ICommunityCellBuildingMapper
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/16
 **/
@Mapper
public interface ICommunityCellBuildingMapper {
    int insertByList(@Param("listCommunityCellBuilding") List<CommunityCellBuilding> listCommunityCellBuilding);

    void batchDeleteByBuildingOrderList(
            @Param("cellUuid") String cellUuid,
            @Param("listBuildingOrder") List<Integer> listBuildingOrder
    );
}
