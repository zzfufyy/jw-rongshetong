package com.shopping.base.utils;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

/**
 * @ClassName CustomerJavaTypeResolver
 * @Description TODO
 * @Author zyw
 * @Date 2022/3/10
 **/
public class CustomerJavaTypeResolver extends JavaTypeResolverDefaultImpl {

    public CustomerJavaTypeResolver() {
        super();
        //把数据库的 TINYINT 映射成 Integer
        super.typeMap.put(Types.TINYINT, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
    }
}
