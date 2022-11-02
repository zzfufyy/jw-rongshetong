package com.shopping.wx.pojo.dto.community_cell;

import com.shopping.wx.model.CommunityCell;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName CommunityCellPlus
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityCellPlus extends CommunityCell implements Serializable {
    String communityName;
}
