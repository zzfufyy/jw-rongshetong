package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.UserCommunity;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.user_community.UserCommunitySerachCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.UserCommunityService;
import com.shopping.wx.util.UUIDUtils;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.data.repository.query.Param;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * @author ljy
 * @date 2022-03-11 16:52
 */
@RestController
@RequestMapping("/user-community")
public class UserCommunityController extends CrudController<UserCommunity, String> {

    final UserCommunityService userCommunityService;

    public UserCommunityController(UserCommunityService userCommunityService) {
        this.userCommunityService = userCommunityService;
    }

    @GetMapping("/page")
    ActionResult<PageInfo<UserCommunity>> page(PagingParam<UserCommunitySerachCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(userCommunityService.page(pagingParam))
        );
    }

    @PostMapping("/add")
    ActionResult<?> add(@RequestBody UserCommunity userCommunity) {
        // 找不到再插入
        if (ObjectUtils.isEmpty(userCommunityService.selectById(userCommunity.getId()))) {
            if (StringUtil.isNotEmpty(userCommunity.getRealName())) {
                String nickName = EmojiParser.parseToHtmlHexadecimal(userCommunity.getRealName());
                userCommunity.setRealName(nickName);
            } else {
                String nickName = AuditConstant.VISTOR_NAME_PREFIX + UUIDUtils.gen8CharUuid();
                userCommunity.setRealName(nickName);
            }
            insert(userCommunity);
        }
        return ActionResult.ok();
    }

    @GetMapping("/info")
    ActionResult<UserCommunity> info(@Param("id") String id) {
        return ActionResult.ok(
                userCommunityService.selectById(id)
        );
    }

    @GetMapping("/loadRecorderListByCommunityUuid")
    ActionResult<List<UserCommunity>> loadRecorderListByCommunityUuid(@RequestParam String communityUuid) {
        return ActionResult.ok(
                userCommunityService.loadRecorderListByCommunityUuid(communityUuid)
        );
    }


    @PostMapping("/modify")
    ActionResult<?> modify(UserCommunity userCommunity) {
        return ActionResult.ok(
                userCommunityService.update(userCommunity)
        );
    }

    @Override
    protected CrudService<UserCommunity> getService() {
        return userCommunityService;
    }
}
