package com.shopping.wx.pojo.vo.user;

import lombok.Data;

/**
 * @author ljy
 * @date 2022-03-22 10:25
 */
@Data
public class UserInfoVO {
    /**
     * openid
     */
    String openid;
    /**
     * 昵称
     */
    String nickname;
    /**
     * 头像 url
     */
    String avatarurl;
}
