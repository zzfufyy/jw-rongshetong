package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.CommunityInformation;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.community_info.CommunityInfoSearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * 社区信息服务
 *
 * @author ljy
 * @date 2022-03-14 9:13
 */
public interface CommunityInfoService extends CrudService<CommunityInformation> {
    /**
     * 根据条件分页查询
     *
     * @param pagingParam 分页条件
     * @return 结果
     */
    List<CommunityInformation> page(PagingParam<CommunityInfoSearchCondition> pagingParam);

    /**
     * 根据条件查询 List
     *
     * @param
     * @return java.util.List<com.shopping.wx.model.CommunityInformation>
     */
    List<CommunityInformation> list(PagingParam<CommunityInfoSearchCondition> pagingParam);

    
    /**
     * 获取指定城市的 市辖区列表
     * 
     * @param  cityName
     * @return  
     */
    List<String> listDistrictNameByCityName(String cityName);

    /**
     * 获取指定市辖区 的街道列表
     *
     * @param  districtName
     * @return
     */
    List<String> listStreetNameByDistrictName(String districtName);

    /**
     * 获取指定街道的 社区信息列表
     *
     * @param  streetName
     * @return
     */
    List<CommunityInformation> listByStreetName(String streetName);

}
