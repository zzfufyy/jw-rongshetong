package com.shopping.wx.interceptor;

import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.util.ReflectionUtils;
import com.shopping.wx.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.util.Date;
import java.util.Properties;


/**
 * @author ljy
 * @date 2022-03-11 11:06
 */
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}
                )
})
@Slf4j
public class AuditFieldInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object object = invocation.getArgs()[1];
        //sql类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if (SqlCommandType.INSERT.equals(sqlCommandType)) {
            // 插入操作的时候，自动为没有设置 uuid 的记录分配一个 uuid
            ReflectionUtils.setFieldIfAbsent(object, AuditConstant.ID_FIELD_NAME, UUIDUtils::randomUUID);
            //插入操作时，自动更新 gen_time 字段
            ReflectionUtils.setFieldIfAbsent(object, AuditConstant.CREATE_TIME_FIELD_NAME, Date::new);
            // 插入操作时，自动更新状态字段, 0 正常，-1 删除
            ReflectionUtils.setFieldIfAbsent(object, AuditConstant.STATUS_FIELD_NAME, AuditConstant.RecordStatus.ACTIVE::value);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
