package com.shopping.wx.pojo.vo.community_building;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CommunityBuildingSearchCondition
 * @Description TODO
 * @Author zyw
 * @Date 2022/9/14
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityBuildingSearchCondition {
    String districtName;
    String streetName;

    String communityUuid;
}
