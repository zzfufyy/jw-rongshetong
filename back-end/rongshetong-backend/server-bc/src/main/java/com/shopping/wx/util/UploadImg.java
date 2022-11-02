package com.shopping.wx.util;

import com.shopping.wx.entity.JSON;
import com.shopping.wx.model.Product;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/upload")
public class UploadImg {

    @RequestMapping("/upload")
    public JSON uploadPicture(@RequestParam(value="contractFile",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        JSON result = new JSON();
        int maxSize = 1024*1024*1024;    //上传最大为1MB
        if (file.getSize()>maxSize) {
            result.setMsg("最大上传限制1G");
            return result;
        }
        request.setCharacterEncoding("utf-8");
        Map<String, Object> map = new HashMap<String, Object>();
        File targetFile=null;
        String url="";//返回存储路径
        String fileName=file.getOriginalFilename();//获取文件名加后缀
        if(fileName!=null&&fileName!=""){
           // String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
            String path = "D:/data/file/";
            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀

            //新的文件名
            fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;
            //获取文件夹路径
            File file1=new File(path);
            //如果文件夹不存在则创建
            if(!file1.exists()  && !file1.isDirectory()){
                file1.mkdirs();
            }
            //将图片存入文件夹
            targetFile = new File(file1, fileName);
            response.setCharacterEncoding("utf-8");
            try {
                //将上传的文件写到服务器上指定的文件。
                file.transferTo(targetFile);

                //赋予权限
                /*String command = "chmod 775 -R " + targetFile;
                Runtime runtime = Runtime.getRuntime();
                Process proc = runtime.exec(command);*/
                //生成文件地址
                url="/"+fileName;
//                OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFile), "utf-8");
//                System.out.println((char)file.getInputStream().read());
//                //oStreamWriter.append((char)file.getInputStream().read());
//                oStreamWriter.close();
                //result.setMsg(200+"");
                System.out.println("文件上传成功 url:"+url);
                result.setObj(url);
            } catch (Exception e) {
                e.printStackTrace();
                result.setMsg("系统异常，文件上传失败");
            }


        }
        return result;
    }


}
