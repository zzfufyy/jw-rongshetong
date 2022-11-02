package com.shopping.wx.managed_mapper.community_recruitment;


import com.shopping.wx.pojo.dto.community_cell.CommunityCellDTO;
import com.shopping.wx.pojo.dto.community_cell.CommunityCellPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @ClassName ICommunityCellMapper
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/14
 **/
@Mapper
public interface ICommunityCellMapper {

    List<CommunityCellDTO> listDTOByCommunityUuid(@Param("communityUuid") String communityUuid);

    CommunityCellPlus infoWithAssociated(@Param("id") String id);
}
