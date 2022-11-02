package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.CommunityCell;
import com.shopping.wx.pojo.dto.community_cell.CommunityCellDTO;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName CommunityCellService
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/11
 **/
public interface CommunityCellService extends CrudService<CommunityCell> {

    CommunityCell infoWithAssociated(String id);

    /**
     * 根据社区id加载小区实体列表
     * 
     * @param  communityUuid
     * @return  
     */
    List<CommunityCell> listByCommunityUuid(String communityUuid);



    /**
     * 根据社区id加载 小区列表   plus: 已注册人数、未注册人数
     * 
     * @param  communityUuid
     * @return  
     */
    List<CommunityCellDTO> listDTOByCommunityUuid(String communityUuid);

    /**
     * 小区添加录入员
     *
     * @param recorderOpenid
     * @param cellUuidList
     * @return int
     */
    int updateByRecorderOpenid(String recorderOpenid, List<String> cellUuidList);
}
