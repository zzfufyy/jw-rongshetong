package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.MessageDialog;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.MessageDialogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO: 还没有实现此接口。如果需要实时进行通信，需要使用 websocket
 * 聊天消息控制器
 *
 * @author ljy
 * @date 2022-03-14 10:44
 */
@RestController
@RequestMapping("/message-dialog")
public class MessageDialogController extends CrudController<MessageDialog, String> {
    final
    MessageDialogService messageDialogService;

    public MessageDialogController(MessageDialogService messageDialogService) {
        this.messageDialogService = messageDialogService;
    }


    // TODO: 重写此分页
    // 根据招聘记录分页查询
    @RequestMapping("/pagedByRecord")
    ActionResult<PageInfo<MessageDialog>> pagedByRecord(PagingParam<String> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(messageDialogService.pagedByRecordId(pagingParam))
        );
    }


    @Override
    protected CrudService<MessageDialog> getService() {
        return messageDialogService;
    }
}
