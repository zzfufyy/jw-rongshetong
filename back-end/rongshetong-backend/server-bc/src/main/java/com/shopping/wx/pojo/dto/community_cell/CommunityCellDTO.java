package com.shopping.wx.pojo.dto.community_cell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName CommunityCellDTO
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/14
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityCellDTO implements Serializable {
    private String id;
    private String cellName;
    private String communityUuid;
    private Integer numBuilding;
    private Integer numLayer;
    private Integer numLayerFamily;
    private String recorderOpenid;
    /** 以下非原始表字段 */
    private String recorderName;
    private Integer numRegistered;
    private Integer numUnregistered;
}
