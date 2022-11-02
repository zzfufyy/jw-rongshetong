package com.shopping.wx.pojo.dto.building_resident;

import com.shopping.wx.model.BuildingResident;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName BuildingResidentDTO
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/25
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingResidentDTO extends BuildingResident implements Serializable {

    // 新增属性
    private String buildingName;
    private String cellName;
    private String communityName;

}
