package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.model.MessageDialog;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.MessageDialogService;
import com.shopping.wx.util.query_utils.QueryUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ljy
 * @date 2022-03-14 10:43
 */
@Service
public class MessageDialogServiceImpl extends CrudServiceImpl<MessageDialog> implements MessageDialogService {

    @Override
    public List<MessageDialog> pagedByRecordId(PagingParam<String> pagingParam) {
        String recordUuid = pagingParam.getCondition();
        return pagingByWhereClauseBuilder(pagingParam.getPage(), (builder) -> builder
                // 未删除
                .notDeleted()
                // 传入的 record uuid 不为空字符串 的时候，才进行分页查询
                .notEmptyEq(recordUuid, MessageDialog::getRecordUuid)
        );
    }

}
