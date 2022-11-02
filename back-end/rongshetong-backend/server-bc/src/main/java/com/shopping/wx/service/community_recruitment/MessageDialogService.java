package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.MessageDialog;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;


/**
 * @author ljy
 * @date 2022-03-14 10:42
 */
public interface MessageDialogService extends CrudService<MessageDialog> {
    /**
     * 根据招聘记录查询对应的会话消息内容，分页
     *
     * @param pagingParam 分页参数
     * @return 查询结果
     */
    List<MessageDialog> pagedByRecordId(PagingParam<String> pagingParam);
}
