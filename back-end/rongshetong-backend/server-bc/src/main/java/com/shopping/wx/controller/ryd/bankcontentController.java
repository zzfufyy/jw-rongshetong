package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.Organ;
import com.shopping.wx.model.Product;
import com.shopping.wx.model.Warrant;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/bankcontent")
public class bankcontentController {

    @Autowired
    private DB db;

    //查询所有银行网点
    @RequestMapping(value = "/searchBankcontent", method = RequestMethod.GET)
    public JSONObject searchBankcontent() throws IOException {
        JSONObject json = new JSONObject();
        Organ organ = new Organ();
        List<Organ> Organlist = db.select(organ);

        for (int i = 0 ;i < Organlist.size();i++){
            String type = Organlist.get(i).getType();
            if(type.equals("0")){
                Organlist.get(i).setType("营业厅");
            }else if (type.equals("1")){
                Organlist.get(i).setType("ATM");
            }
        }

        json.put("bankcontent",Organlist);
        return json;
    }

    //查询银行id查询银行网点
    @RequestMapping(value = "/searchBankcontentById", method = RequestMethod.GET)
    public JSONObject searchBankcontentById(String id) throws IOException {
        JSONObject json = new JSONObject();
        Organ organ = new Organ();
        organ.setBankid(id);
        List<Organ> Organlist = db.select(organ);

        for (int i = 0 ;i < Organlist.size();i++){
            String type = Organlist.get(i).getType();
            if(type.equals("0")){
                Organlist.get(i).setType("营业厅");
            }else if (type.equals("1")){
                Organlist.get(i).setType("ATM");
            }
        }

        json.put("bankcontent",Organlist);
        return json;
    }

    //查询所有机构产品
    @RequestMapping(value = "/searchProduct", method = RequestMethod.GET)
    public JSONObject searchProduct(){
        JSONObject json = new JSONObject();
        Product product = new Product();
        List<Product> productList = db.select(product);
        json.put("productList",productList);
        return json;
    }

    //根据id查询机构产品
    @RequestMapping(value = "/selectProductById", method = RequestMethod.GET)
    public JSONObject selectProductById(String id){
        JSONObject json = new JSONObject();
        Product product = db.selectById(id,Product.class);
        json.put("product",product);
        return json;
    }

    //根据银行id查询机构产品
    @RequestMapping(value = "/selectProductByBankId", method = RequestMethod.GET)
    public JSONObject selectProductByBankId(String id){
        JSONObject json = new JSONObject();
        Organ organ = new Organ();
        organ.setId(id);
        List<Organ> organList = db.select(organ);
        Product product = new Product();
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i <organList.size() ; i++) {
            product.setBankid(organList.get(i).getId());
            List<Product> productList1 = db.select(product);
            productList.addAll(productList1);
        }

        json.put("productList",productList);
        return json;
    }

    @RequestMapping(value = "/selectrzdb", method = RequestMethod.GET)
    public JSONObject selectrzdb(String id){
        JSONObject json = new JSONObject();
        List<Warrant>  warrants=db.selectAll(Warrant.class);
        json.put("warrants",warrants);
        return json;
    }
}
