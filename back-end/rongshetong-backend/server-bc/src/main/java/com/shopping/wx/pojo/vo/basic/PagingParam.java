package com.shopping.wx.pojo.vo.basic;

import com.shopping.wx.conf.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ljy
 * @date 2022-03-14 15:15
 * <p>
 * 分页参数和包含的搜索条件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagingParam<T> implements Serializable {

    Page page;
    /**
     * 分页附带的搜索条件，注意需要检查条件是否为 null，
     */
    T condition;
}
