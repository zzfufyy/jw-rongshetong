package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.managed_mapper.community_recruitment.ICommunityCellMapper;
import com.shopping.wx.mapper.CommunityCellMapper;
import com.shopping.wx.model.CommunityCell;
import com.shopping.wx.pojo.dto.community_cell.CommunityCellDTO;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.CommunityCellService;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.List;

/**
 * @ClassName CommunityCellServiceImpl
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/11
 **/
@Service
public class CommunityCellServiceImpl extends CrudServiceImpl<CommunityCell> implements CommunityCellService {
    @Autowired
    private ICommunityCellMapper iCommunityCellMapper;
    @Autowired
    private CommunityCellMapper communityCellMapper;

    @Override
    public CommunityCell infoWithAssociated(String id) {
        return iCommunityCellMapper.infoWithAssociated(id);
    }

    @Override
    public List<CommunityCell> listByCommunityUuid(String communityUuid) {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<CommunityCell>()
                        .notEmptyEq(communityUuid, CommunityCell::getCommunityUuid)
                        .notDeleted()
                        .getSqls()
        );
        return communityCellMapper.selectByExample(builder.build());
    }

    @Override
    public List<CommunityCellDTO> listDTOByCommunityUuid(String communityUuid) {
        return iCommunityCellMapper.listDTOByCommunityUuid(communityUuid);
    }

    @Override
    public int updateByRecorderOpenid(String recorderOpenid, List<String> cellUuidList) {
        CommunityCell communityCell = new CommunityCell();
        communityCell.setRecorderOpenid(recorderOpenid);
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(Sqls.custom().andIn(
                QueryUtils.getFieldName(CommunityCell::getId),
                cellUuidList));
        return communityCellMapper.updateByExampleSelective(communityCell, builder.build());

    }

}
