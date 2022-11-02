package com.shopping.wx.pojo.vo.community_info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ljy
 * @date 2022-03-15 11:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityInfoSearchCondition implements Serializable {

    String name;

    String cityName;
}
