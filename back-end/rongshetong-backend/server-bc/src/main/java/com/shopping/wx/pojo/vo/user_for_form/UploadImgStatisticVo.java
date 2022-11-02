package com.shopping.wx.pojo.vo.user_for_form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName UploadImgStatisticVo
 * @Description TODO
 * @Author zyw
 * @Date 2022/8/15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadImgStatisticVo implements Serializable {
    Integer componentType;
    Integer subjectType;
    String userOpenid;
    String subjectAnswer;
    String uploadImg;
    String optionUuid;
    String subjectUuid;
    String formUuid;
}
