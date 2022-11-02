package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.conf.DB;
import com.shopping.wx.conf.Page;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.entity.JSON;
import com.shopping.wx.model.JobCategory;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.job_category.JobCategorySearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.JobCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * @ClassName JobCategoryController
 * @Description 岗位分类接口  --  1级分类
 * @Author zyw
 * @Date 2022/3/14
 **/
@RestController
@RequestMapping("job-category")
public class JobCategoryController extends CrudController<JobCategory, String> {
    @Autowired
    private JobCategoryService jobCategoryService;

    @Autowired
    private DB db;

    @RequestMapping("/add")
    public ActionResult<String> add(@RequestBody JobCategory jobCategory) {
        // 先查找 无重复插入
        List<JobCategory> list = jobCategoryService.select(jobCategory);
        if (list.size() == 0) {
            insert(jobCategory);
        }
        return ActionResult.ok(list.size() == 0 ? jobCategory.getId() : list.get(0).getId());
    }

    @RequestMapping("/page")
    public ActionResult<PageInfo<JobCategory>> page(@RequestBody PagingParam<JobCategorySearchCondition> pagingParam) {
        return ActionResult.ok(PageInfo.of(jobCategoryService.page(pagingParam)));
    }

    @GetMapping("/info")
    public ActionResult<JobCategory> info(String id) {
        return ActionResult.ok(infoVO(id, JobCategory::new));
    }


    @RequestMapping("/modify")
    public ActionResult<Boolean> modify(@RequestBody JobCategory jobCategory) {
        return ActionResult.ok(updateVO(jobCategory));
    }


    @RequestMapping("/incrCountView")
    public ActionResult<Boolean> incrCountView(@RequestParam String id) {
        return jobCategoryService.incrCountView(id) ? ActionResult.ok() : ActionResult.error();
    }

    /**
     * 返回职位列表list  -- 查询条件： category:模糊传值
     *
     * @param jobCategory
     * @return com.shopping.base.foundation.result.ActionResult<java.util.List               <               com.shopping.wx.model.JobCategory>>
     */
    @RequestMapping("/list")
    ActionResult<List<JobCategory>> list(@RequestBody(required = false) JobCategory jobCategory) {
        String categoryName = jobCategory == null ? null : jobCategory.getCategoryName();
        return ActionResult.ok(jobCategoryService.list(categoryName));
    }

    @GetMapping("/listByNumber")
    ActionResult<List<JobCategory>> listByNumber(@RequestParam Integer number, @RequestParam String categoryName) {
        return ActionResult.ok(jobCategoryService.listByNumber(number,categoryName));
    }



    //查询岗位
    @RequestMapping("/listbyyx")
    public JSON listbyyx(String name, String keyword, Page page) {
        JSON json = new JSON();
        page.setPage(1);
        page.setRows(5);
        Example example = new Example(JobCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 0);
        if (name != null && !name.equals("")) {
            System.out.println(name);
            criteria.andLike("categoryName", "%" + name + "%");
//            criteria.andLike("keyword","%"+name+"%");
        }
        example.setOrderByClause("count_view desc,priority desc");
        List<JobCategory> jobCategoryList = db.selectPageByExample(JobCategory.class, example, page);
//        if(jobCategoryList.size()==0){
//            JobCategory jobCategory = new JobCategory();
//            jobCategory.setId(UUIDUtils.randomUUID());
//            jobCategory.setCategoryName(name);
//            jobCategory.setCreateTime(new Date());
//            jobCategory.setStatus(0);
//            jobCategory.setCountView(0);
//            jobCategory.setPriority(0);
//            db.insert(jobCategory);
//
//        }
        json.setFlag(true);
        json.setObj(jobCategoryList);
        return json;
    }


    @Override
    protected CrudService<JobCategory> getService() {
        return jobCategoryService;
    }


}




