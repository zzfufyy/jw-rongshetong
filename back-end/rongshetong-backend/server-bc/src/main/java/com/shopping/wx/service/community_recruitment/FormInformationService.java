package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.FormInformation;
import com.shopping.wx.pojo.dto.form_information.FormInformationPlus;
import com.shopping.wx.pojo.dto.form_information.FormInformationWithStatisticDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.form_information.FormInformationSearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName FormInformationService
 * @Description TODO
 * @Author zyw
 * @Date 2022/6/14
 **/
public interface FormInformationService extends CrudService<FormInformation> {

    /**
     * 正常分页 (status in(0,1))
     *
     * @param pagingParam
     * @return java.util.List<com.shopping.wx.pojo.dto.form_information.FormInformationPlus>
     */
    List<FormInformationPlus> pageNormal(PagingParam<FormInformationSearchCondition> pagingParam);

    /**
     * 下架分页 ( status = -1)
     *
     * @param pagingParam
     * @return java.util.List<com.shopping.wx.pojo.dto.form_information.FormInformationPlus>
     */
    List<FormInformationPlus> pageUnpublished(PagingParam<FormInformationSearchCondition> pagingParam);

    /**
     * 投票活动分页（根据社区）
     *
     * @param pagingParam
     * @return java.util.List<com.shopping.wx.pojo.dto.form_information.FormInformationPlus>
     */
    List<FormInformationPlus> pagePublished(PagingParam<FormInformationSearchCondition> pagingParam);


    FormInformationWithStatisticDTO infoWithStatistic(String id);
}
