package com.shopping.wx.pojo.vo.infomation_news;

import com.shopping.wx.pojo.vo.basic.TimeCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ljy
 * @date 2022-03-14 16:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoNewsSearchCondition extends TimeCondition implements Serializable {
    /**
     * 文章标题，模糊查询
     */
    String articleTitle;

    /**
     * 社区 id
     */
    String communityUuid;

    /**
     * 社区类型 参考 AuditConstant.CommunityType
     */
    Integer communityType;

    /**
     * 文章类型
     */
    Integer articleType;
}
