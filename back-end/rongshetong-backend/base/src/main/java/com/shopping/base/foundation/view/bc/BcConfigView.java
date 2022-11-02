package com.shopping.base.foundation.view.bc;

import com.shopping.base.domain.bc.BcConfig;
import com.shopping.base.foundation.view.BeanView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class BcConfigView  extends BeanView<BcConfig>  implements Serializable {

    public Long id;

    private String appId;
    /** 用户是否需要审核 */
    private boolean userNeedApprove ;
    /** 周六是否可报餐 */
    private boolean saturdayCanDiner ;
    /** 周日是否可报餐 */
    private boolean sundayCanDiner ;
    /** 午餐报餐时间*/
    private String lunchOrderTime;
    /** 晚餐报餐时间 */
    private String dinnerOrderTime;
    /** 午餐开始报餐时间 */
    private String lunchStartTime;
    /** 午餐结束报餐时间 */
    private String lunchEndTime;
    /** 晚餐开始报餐时间 */
    private String dinnerStartTime;
    /** 晚餐结束报餐时间 */
    private String dinnerEndTime;
    /** 午餐截止时间 */
    private String lunchJzTime;
    /** 晚餐截止时间 */
    private String dinnerJzTime;

    private String endTime;
    /**  午餐是否可报餐*/
    private boolean lunchCanMeal;
    /** 晚餐是否可报餐 */
    private boolean dinnerCanMeal;

    /** 单位（食堂）ID */
    @Column(name = "user_bm_id")
    private Long userBmId;
}
