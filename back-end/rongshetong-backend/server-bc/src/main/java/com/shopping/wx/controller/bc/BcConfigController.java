package com.shopping.wx.controller.bc;

import com.alibaba.fastjson.JSON;
import com.shopping.base.domain.bc.BcConfig;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.base.foundation.view.BeanViewUtils;
import com.shopping.base.foundation.view.bc.BcConfigView;
import com.shopping.wx.service.bc.BcConfigService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/bc/{appid}/config")
public class BcConfigController  {

    @Autowired
    private BcConfigService bcConfigService;


    /**
     *获取配置
     * @return
     */
    @GetMapping("/getConfig")
    public ActionResult getConfig(@PathVariable String appid, Long deptId){
        try{
            BcConfig cfg= this.bcConfigService.getConfigByAppId(appid, deptId);
            BcConfigView bcConfigView = BeanViewUtils.getView(cfg,BcConfigView.class);
            return  ActionResult.ok(bcConfigView);
        }catch (Exception e){
            log.error("获取截止时间异常",e);
        }
        return ActionResult.error("服务器异常");
    }

    /**
     * 保存或者更新配置
     * @param appid
     * @param config
     * @return
     */
    @GetMapping("/saveOrUpdate")
    public ActionResult saveOrUpdate(@PathVariable String appid,String config1,String imgUrlList){
        BcConfig config =JSON.parseObject(config1,BcConfig.class);
        try{
            return this.bcConfigService.saveOrUpdate(appid,config,imgUrlList);
        }catch (Exception e){
            log.error("保存或者更新配置失败!",e);
        }
        return ActionResult.error("服务器异常");
    }


}
