package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.pojo.dto.view_record.ViewRecordDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ljy
 * @date 2022-03-25 14:45
 */

@Mapper
public interface IViewRecordMapper {
    /**
     * 根据位置进行分页查询
     *
     * @param  candidateOpenid 求职者id
     * @return 结果
     */
    List<ViewRecordDTO> pagedByDistance(@Param("candidateOpenid") String candidateOpenid);

}
