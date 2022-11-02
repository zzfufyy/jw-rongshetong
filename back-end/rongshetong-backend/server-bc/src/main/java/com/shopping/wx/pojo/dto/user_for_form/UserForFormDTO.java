package com.shopping.wx.pojo.dto.user_for_form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName UserForFormDTO
 * @Description TODO
 * @Author zyw
 * @Date 2022/6/27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForFormDTO implements Serializable {
    private Integer componentType;
    private Integer subjectType;
    private String userOpenid;
    private String subjectAnswer;
    private String uploadImg;
//    private String optionUuid;
    private List<String> optionList;
    private String subjectUuid;
    private String formUuid;
}
