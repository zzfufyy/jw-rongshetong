package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "information_news")
public class InformationNews {
    /**
     * uuid 主键
     */
    @Id
    private String id;

    /**
     * 文章标题
     */
    @Column(name = "article_title")
    private String articleTitle;

    /**
     * 文章资讯类型 0: 普通  1：空缺（不确定） 2: 公告 3：招聘新闻 4: 优秀毕业生 5:事业单位 6: 公务员 7: 选调生 8: 教育培训 9: 教育招考  10： 三支一扶/支教扶贫
     */
    @Column(name = "article_type")
    private Short articleType;

    /**
     * 文章关键词
     */
    @Column(name = "article_keyword")
    private String articleKeyword;

    /**
     * 文章简介
     */
    @Column(name = "article_introduction")
    private String articleIntroduction;

    /**
     * 文章作者（机构）
     */
    @Column(name = "article_author")
    private String articleAuthor;

    /**
     * 文章发布时间
     */
    @Column(name = "article_release_time")
    private Date articleReleaseTime;

    /**
     * 文章附件路径
     */
    @Column(name = "attachment_path")
    private String attachmentPath;

    /**
     * 文章缩略图路径
     */
    @Column(name = "article_portrait_path")
    private String articlePortraitPath;

    /**
     * 作用页面路径（如有单独页面放新闻）
     */
    @Column(name = "page_path_apply")
    private String pagePathApply;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 关联 - 录入人用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 关联 - 录入员工真实姓名
     */
    @Column(name = "staff_name")
    private String staffName;

    /**
     * 该条新闻发布社区
     */
    @Column(name = "community_uuid")
    private String communityUuid;

    /**
     * 浏览量
     */
    @Column(name = "count_view")
    private Integer countView;

    /**
     * 记录状态（0：正常 -1：不在小程序显示）
     */
    private Integer status;

    /**
     * 信息录入时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 文章附件名
     */
    private String ext1;

    /**
     * 扩展字段2
     */
    private String ext2;

    /**
     * 扩展字段3
     */
    private String ext3;

    /**
     * 文章内容（富文本）
     */
    @Column(name = "article_content")
    private String articleContent;

    /**
     * 获取uuid 主键
     *
     * @return id - uuid 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置uuid 主键
     *
     * @param id uuid 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取文章标题
     *
     * @return article_title - 文章标题
     */
    public String getArticleTitle() {
        return articleTitle;
    }

    /**
     * 设置文章标题
     *
     * @param articleTitle 文章标题
     */
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    /**
     * 获取文章资讯类型 0: 普通  1：空缺（不确定） 2: 公告 3：招聘新闻 4: 优秀毕业生 5:事业单位 6: 公务员 7: 选调生 8: 教育培训 9: 教育招考  10： 三支一扶/支教扶贫
     *
     * @return article_type - 文章资讯类型 0: 普通  1：空缺（不确定） 2: 公告 3：招聘新闻 4: 优秀毕业生 5:事业单位 6: 公务员 7: 选调生 8: 教育培训 9: 教育招考  10： 三支一扶/支教扶贫
     */
    public Short getArticleType() {
        return articleType;
    }

    /**
     * 设置文章资讯类型 0: 普通  1：空缺（不确定） 2: 公告 3：招聘新闻 4: 优秀毕业生 5:事业单位 6: 公务员 7: 选调生 8: 教育培训 9: 教育招考  10： 三支一扶/支教扶贫
     *
     * @param articleType 文章资讯类型 0: 普通  1：空缺（不确定） 2: 公告 3：招聘新闻 4: 优秀毕业生 5:事业单位 6: 公务员 7: 选调生 8: 教育培训 9: 教育招考  10： 三支一扶/支教扶贫
     */
    public void setArticleType(Short articleType) {
        this.articleType = articleType;
    }

    /**
     * 获取文章关键词
     *
     * @return article_keyword - 文章关键词
     */
    public String getArticleKeyword() {
        return articleKeyword;
    }

    /**
     * 设置文章关键词
     *
     * @param articleKeyword 文章关键词
     */
    public void setArticleKeyword(String articleKeyword) {
        this.articleKeyword = articleKeyword == null ? null : articleKeyword.trim();
    }

    /**
     * 获取文章简介
     *
     * @return article_introduction - 文章简介
     */
    public String getArticleIntroduction() {
        return articleIntroduction;
    }

    /**
     * 设置文章简介
     *
     * @param articleIntroduction 文章简介
     */
    public void setArticleIntroduction(String articleIntroduction) {
        this.articleIntroduction = articleIntroduction == null ? null : articleIntroduction.trim();
    }

    /**
     * 获取文章作者（机构）
     *
     * @return article_author - 文章作者（机构）
     */
    public String getArticleAuthor() {
        return articleAuthor;
    }

    /**
     * 设置文章作者（机构）
     *
     * @param articleAuthor 文章作者（机构）
     */
    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor == null ? null : articleAuthor.trim();
    }

    /**
     * 获取文章发布时间
     *
     * @return article_release_time - 文章发布时间
     */
    public Date getArticleReleaseTime() {
        return articleReleaseTime;
    }

    /**
     * 设置文章发布时间
     *
     * @param articleReleaseTime 文章发布时间
     */
    public void setArticleReleaseTime(Date articleReleaseTime) {
        this.articleReleaseTime = articleReleaseTime;
    }

    /**
     * 获取文章附件路径
     *
     * @return attachment_path - 文章附件路径
     */
    public String getAttachmentPath() {
        return attachmentPath;
    }

    /**
     * 设置文章附件路径
     *
     * @param attachmentPath 文章附件路径
     */
    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath == null ? null : attachmentPath.trim();
    }

    /**
     * 获取文章缩略图路径
     *
     * @return article_portrait_path - 文章缩略图路径
     */
    public String getArticlePortraitPath() {
        return articlePortraitPath;
    }

    /**
     * 设置文章缩略图路径
     *
     * @param articlePortraitPath 文章缩略图路径
     */
    public void setArticlePortraitPath(String articlePortraitPath) {
        this.articlePortraitPath = articlePortraitPath == null ? null : articlePortraitPath.trim();
    }

    /**
     * 获取作用页面路径（如有单独页面放新闻）
     *
     * @return page_path_apply - 作用页面路径（如有单独页面放新闻）
     */
    public String getPagePathApply() {
        return pagePathApply;
    }

    /**
     * 设置作用页面路径（如有单独页面放新闻）
     *
     * @param pagePathApply 作用页面路径（如有单独页面放新闻）
     */
    public void setPagePathApply(String pagePathApply) {
        this.pagePathApply = pagePathApply == null ? null : pagePathApply.trim();
    }

    /**
     * 获取优先级
     *
     * @return priority - 优先级
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 设置优先级
     *
     * @param priority 优先级
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 获取关联 - 录入人用户id
     *
     * @return user_id - 关联 - 录入人用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置关联 - 录入人用户id
     *
     * @param userId 关联 - 录入人用户id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取关联 - 录入员工真实姓名
     *
     * @return staff_name - 关联 - 录入员工真实姓名
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * 设置关联 - 录入员工真实姓名
     *
     * @param staffName 关联 - 录入员工真实姓名
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    /**
     * 获取该条新闻发布社区
     *
     * @return community_uuid - 该条新闻发布社区
     */
    public String getCommunityUuid() {
        return communityUuid;
    }

    /**
     * 设置该条新闻发布社区
     *
     * @param communityUuid 该条新闻发布社区
     */
    public void setCommunityUuid(String communityUuid) {
        this.communityUuid = communityUuid == null ? null : communityUuid.trim();
    }

    /**
     * 获取浏览量
     *
     * @return count_view - 浏览量
     */
    public Integer getCountView() {
        return countView;
    }

    /**
     * 设置浏览量
     *
     * @param countView 浏览量
     */
    public void setCountView(Integer countView) {
        this.countView = countView;
    }

    /**
     * 获取记录状态（0：正常 -1：不在小程序显示）
     *
     * @return status - 记录状态（0：正常 -1：不在小程序显示）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置记录状态（0：正常 -1：不在小程序显示）
     *
     * @param status 记录状态（0：正常 -1：不在小程序显示）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取信息录入时间
     *
     * @return create_time - 信息录入时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置信息录入时间
     *
     * @param createTime 信息录入时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取文章附件名
     *
     * @return ext1 - 文章附件名
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * 设置文章附件名
     *
     * @param ext1 文章附件名
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    /**
     * 获取扩展字段2
     *
     * @return ext2 - 扩展字段2
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * 设置扩展字段2
     *
     * @param ext2 扩展字段2
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    /**
     * 获取扩展字段3
     *
     * @return ext3 - 扩展字段3
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * 设置扩展字段3
     *
     * @param ext3 扩展字段3
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    /**
     * 获取文章内容（富文本）
     *
     * @return article_content - 文章内容（富文本）
     */
    public String getArticleContent() {
        return articleContent;
    }

    /**
     * 设置文章内容（富文本）
     *
     * @param articleContent 文章内容（富文本）
     */
    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }
}