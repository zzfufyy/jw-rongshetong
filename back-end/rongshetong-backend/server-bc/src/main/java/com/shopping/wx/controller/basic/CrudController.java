package com.shopping.wx.controller.basic;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.function.Supplier;

/**
 * 基础增删查改控制器
 *
 * @param <T>  目标实体
 * @param <ID> 目标实体的 id 类型
 * @author ljy
 * @date 2022-03-11 11:06
 */
@Slf4j
public abstract class CrudController<T, ID> extends AbstractController {

    /**
     * 以 VO 的方式添加
     *
     * @param source VO 对象
     * @return 是否成功
     */
    protected <VO> boolean insertVO(VO source) {
        try {
            getService().insert(castEntity(source));
            return true;
        } catch (ReflectiveOperationException e) {
            log.error("Can not instantiate {} because of {}", ((CrudServiceImpl<T>) getService()).getEntityClass(), e);
            return false;
        }
        // TODO: 解决异常后会影响下一次接口调用
//        catch (Exception exception) {
//            log.error("Insert failed", exception);
//            return false;
//        }
    }

    /**
     * 以 VO 的方式修改
     *
     * @param source VO 对象
     * @return 是否成功
     */
    protected <VO> boolean updateVO(VO source) {
        try {
            getService().update(castEntity(source));
            return true;
        } catch (ReflectiveOperationException e) {
            log.error("Can not instantiate {} because of {}", ((CrudServiceImpl<T>) getService()).getEntityClass(), e);
            return false;
        }
    }

    /**
     * 根据给定的 id 查询实体，并且映射成对应类型的 VO
     *
     * @param id    给定的 id
     * @param newVO VO 的构造器
     * @param <VO>  VO 类型
     * @return 构造的 VO
     */
    protected <VO> VO infoVO(ID id, Supplier<VO> newVO) {

        return castVO(
                getService().selectById(id),
                newVO
        );
    }

    /**
     * 以记录的方式添加
     *
     * @param record 记录
     */
    protected int insert(T record) {
        return getService().insert(record);
    }

    /**
     * 以记录的方式修改
     *
     * @param record 记录
     */
    protected int update(T record) {
        return getService().update(record);
    }


    /**
     * 查询完整的实体信息，注意不要返回不安全的信息，如用户密码等
     *
     * @param id 实体 id
     * @return 实体
     */
    protected T query(ID id) {
        return getService().selectById(id);
    }

    /**
     * TODO: 使用 @DeleteMapping，为操作添加 msg.   e.g. ActionResult.ok("delete success")
     * <p>
     * 逻辑删除
     *
     * @param id 实体 id
     * @return 删除结果
     */
    @RequestMapping("/logicDelete")
    public ActionResult<Boolean> logicDelete(ID id) {
        return getService().logicDeleteById(id) ?
                ActionResult.ok() :
                ActionResult.error();
    }


    /**
     * 反射创建目标实体对象。
     * <p>
     * 确保给定的实体对象有无参数（即默认的）构造器
     *
     * @return 创建的对象
     * @throws IllegalAccessException 非法访问构造器
     * @throws InstantiationException 创建时异常
     */
    protected T newEntityInstance() throws IllegalAccessException, InstantiationException {
        return ((CrudServiceImpl<T>) getService()).getEntityClass().newInstance();
    }

    /**
     * 子类需要实现此方法以指定服务
     *
     * @return 对应此实体的增删查改服务
     */
    protected abstract CrudService<T> getService();

    /**
     * 将 VO 转换成 实体对象
     *
     * @param source VO 对象
     * @return 转换形成的实体
     * <p>
     * TODO: 提取到工具类
     */
    protected <VO> T castEntity(VO source) throws InstantiationException, IllegalAccessException {
        if (source == null) {
            return null;
        }
        T instance = newEntityInstance();
        BeanUtils.copyProperties(source, instance);
        return instance;
    }

    /**
     * 将 实体对象 转换成 VO
     *
     * @param source 实体对象
     * @param newVO  VO 构造器，必须是无参构造
     * @param <VO>   VO lei
     * @return 转换形成的 VO
     * <p>
     * TODO: 提取到工具类
     */
    protected <VO> VO castVO(T source, Supplier<VO> newVO) {
        if (source == null) {
            return null;
        }
        VO vo = newVO.get();
        BeanUtils.copyProperties(source, vo);
        return vo;
    }
}
