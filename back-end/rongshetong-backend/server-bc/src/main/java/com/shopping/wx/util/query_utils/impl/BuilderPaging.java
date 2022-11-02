package com.shopping.wx.util.query_utils.impl;

import com.shopping.wx.util.query_utils.Paging;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.function.Consumer;

/**
 * @author ljy
 * @date 2022-03-14 13:53
 */
@Slf4j
public class BuilderPaging<T, V> implements Paging<T> {

    protected Consumer<Example.Builder> consumer;

    /**
     * 调用者自定义 consumer 实现分页构造
     *
     * @param consumer 消费者，用于自定义分页构造
     */
    public BuilderPaging(Consumer<Example.Builder> consumer) {
        this.consumer = consumer;
    }

    protected Example doBuild(Class<T> klass) {
        Example.Builder builder = Weekend.builder(klass);
        if (consumer != null) {
            consumer.accept(builder);
        }
        return builder.build();
    }

    @Override
    public Example build(Class<T> klass) {
        return doBuild(klass);
    }
}
