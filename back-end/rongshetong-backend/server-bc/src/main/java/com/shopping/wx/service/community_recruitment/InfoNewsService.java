package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.InformationNews;
import com.shopping.wx.model.RecruitJob;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.infomation_news.InfoNewsSearchCondition;
import com.shopping.wx.pojo.vo.recruit_job.RecruitJobSearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @author ljy
 */
public interface InfoNewsService extends CrudService<InformationNews> {

    /**
     * 对新闻列表进行分页查询
     *
     * @param pagingParam 查询条件
     * @return 结果
     */
    List<InformationNews> page(PagingParam<InfoNewsSearchCondition> pagingParam);

    /**
     * 对新闻列表进行分页查询，以articleType进行分页
     *
     * @param pagingParam 查询条件
     * @return 结果
     */
    List<InformationNews> pageByArticleType(PagingParam<InfoNewsSearchCondition> pagingParam);
    /**
     * 所有蓉社通新闻
     *
     * @param pagingParam
     * @return java.util.List<com.shopping.wx.model.InformationNews>
     */
    List<InformationNews> pageAllSocial(PagingParam<InfoNewsSearchCondition> pagingParam);
    /**
     * 获取列表
     *
     * @param
     * @return java.util.List<com.shopping.wx.model.InformationNews>
     */
    List<InformationNews> list();

    /**
     * 根据社区id获取列表
     *
     * @param communityUuid
     * @return java.util.List<com.shopping.wx.model.InformationNews>
     */
    List<InformationNews> listByCommunityUuid(String communityUuid, Integer num);


    /**
     * 根据社区id 和 作用路由  获取该页面存在的新闻
     *
     * @param communityUuid  社区id
     * @param pagePathApply 页面作用路由
     * @return java.util.List<com.shopping.wx.model.InformationNews>
     */
    List<InformationNews> listByPagePath(String communityUuid, String pagePathApply);

    int increaseCountView(String id);

}
