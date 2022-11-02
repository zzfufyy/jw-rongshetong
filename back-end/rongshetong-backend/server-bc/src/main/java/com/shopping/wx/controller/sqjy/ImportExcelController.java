package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.base.utils.HttpRequest;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.RecruitCompany;
import com.shopping.wx.model.UserCandidate;
import com.shopping.wx.service.basic.UploadService;
import com.shopping.wx.service.community_recruitment.RecruitCompanyService;
import com.shopping.wx.service.community_recruitment.UserRecruiterService;
import com.shopping.wx.util.UUIDUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/import_excel")
public class ImportExcelController {
    @Autowired
    private DB db;
    @Autowired
    UploadService uploadService;
    @Autowired
    private RecruitCompanyService recruitCompanyService;
    @Autowired
    private UserRecruiterService userRecruiterService;
    @RequestMapping("/uploadPortrait")
    public ActionResult<Boolean> uploadPortrait(@RequestParam String id, MultipartFile file, HttpServletRequest request ) throws IOException {
        // 可传 自定义文件夹名
        UploadService.UploadResult uploadResult = uploadService.uploadFile(file, "");

        File files = new File("D:/data/file/"+uploadResult.getUploadUriPath().split("/images")[1]);
        FileInputStream fis = new FileInputStream(files);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("Sheet1");
        int lastRowNum = sheet.getLastRowNum();
        Cell userCell;
        for(int i = 1; i <= lastRowNum; i++){
            Map<String, String> userData = new HashMap<>();
            RecruitCompany recruitCompany = new RecruitCompany();
            recruitCompany.setId(UUIDUtils.randomUUID());
            recruitCompany.setLat(28.227780);
            recruitCompany.setLon(112.938860);
            recruitCompany.setPriority(0);
            recruitCompany.setFlagIdentification(1);
            recruitCompany.setCountView(0);
            recruitCompany.setStatus(0);
            recruitCompany.setCreateTime(new Date());
            recruitCompany.setCommunityUuid(id);
            recruitCompany.setExt2("sqll");
            Row row = sheet.getRow(i);
            userCell = row.getCell(0);  //获取单元格
            userCell.setCellType(CellType.STRING);  //设置单元格类型
            String gsname = userCell.getStringCellValue();  //获取单元格数据
            recruitCompany.setCompanyName(gsname);

            userCell = row.getCell(1);  //获取单元格
            userCell.setCellType(CellType.STRING);  //设置单元格类型
            String tyshxydm = userCell.getStringCellValue();  //获取单元格数据
            recruitCompany.setLicenseId(tyshxydm);

            userCell = row.getCell(2);  //获取单元格
            userCell.setCellType(CellType.STRING);  //设置单元格类型
            String gsjj = userCell.getStringCellValue();  //获取单元格数据
            recruitCompany.setIntroduction(gsjj);

            userCell = row.getCell(3);  //获取单元格
            userCell.setCellType(CellType.STRING);  //设置单元格类型
            String gsdh = userCell.getStringCellValue();  //获取单元格数据
            recruitCompany.setJuridicalPhone(gsdh);
            recruitCompany.setPhone(gsdh);
            userCell = row.getCell(4);  //获取单元格
            userCell.setCellType(CellType.STRING);  //设置单元格类型
            String gsdz = userCell.getStringCellValue();  //获取单元格数据
            recruitCompany.setAddress(gsdz);

            db.insert(recruitCompany);

            System.out.println(userData);
        }
        if (uploadResult.getSuccess()) {
            System.out.println(uploadResult.getUploadUriPath());
            return ActionResult.ok(true);
        } else {
            return ActionResult.error(uploadResult.getMsg());
        }
    }




    @RequestMapping("/uploadQzzPortrait")
    public ActionResult<Boolean> uploadQzzPortrait(@RequestParam String id, MultipartFile file, HttpServletRequest request ) throws IOException {
        // 可传 自定义文件夹名
        UploadService.UploadResult uploadResult = uploadService.uploadFile(file, "");

        File files = new File("D:/data/file/"+uploadResult.getUploadUriPath().split("/images")[1]);
        FileInputStream fis = new FileInputStream(files);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("Sheet1");
        int lastRowNum = sheet.getLastRowNum();
        Cell userCell;
        for(int i = 1; i <= lastRowNum; i++){
            Map<String, String> userData = new HashMap<>();
            UserCandidate userCandidate = new UserCandidate();
            userCandidate.setId(UUIDUtils.randomUUID());
            userCandidate.setLat(28.227780);
            userCandidate.setLon(112.938860);
            userCandidate.setPriority(0);
            userCandidate.setFlagIdentification(1);
            userCandidate.setCountView(0);
            userCandidate.setStatus(0);
            userCandidate.setCreateTime(new Date());
            userCandidate.setCommunityUuid(id);
            userCandidate.setExt2("sqll");
            Row row = sheet.getRow(i);
            userCell = row.getCell(0);  //获取单元格
            userCell.setCellType(CellType.STRING);  //设置单元格类型
            String rlname = userCell.getStringCellValue();  //获取单元格数据
            userCandidate.setRealName(rlname);

            userCell = row.getCell(1);  //获取单元格
            userCell.setCellType(CellType.STRING);  //设置单元格类型
            String sfz = userCell.getStringCellValue();  //获取单元格数据
            userCandidate.setIdentityCard(sfz);

            userCell = row.getCell(2);  //获取单元格
            userCell.setCellType(CellType.STRING);  //设置单元格类型
            String xb = userCell.getStringCellValue();  //获取单元格数据
            int gender = 0;
            if(xb.equals("男")){
                gender=1;
            }else {
                gender=0;
            }
            userCandidate.setGender(gender);

            userCell = row.getCell(3);  //获取单元格
            userCell.setCellType(CellType.STRING);  //设置单元格类型
            String phone = userCell.getStringCellValue();  //获取单元格数据
            userCandidate.setTelephone(phone);

            userCell = row.getCell(4);  //获取单元格
            userCell.setCellType(CellType.STRING);  //设置单元格类型
            String grdz = userCell.getStringCellValue();  //获取单元格数据
            userCandidate.setAddress(grdz);

            userCell = row.getCell(5);  //获取单元格
            userCell.setCellType(CellType.STRING);  //设置单元格类型
            String grjj = userCell.getStringCellValue();  //获取单元格数据
            userCandidate.setIntroduction(grjj);

            userCell = row.getCell(6);  //获取单元格
            userCell.setCellType(CellType.STRING);  //设置单元格类型
            String minsalay = userCell.getStringCellValue();  //获取单元格数据
            userCandidate.setExpectSalaryMin(Integer.valueOf(minsalay));

            userCell = row.getCell(7);  //获取单元格
            userCell.setCellType(CellType.STRING);  //设置单元格类型
            String maxsalay = userCell.getStringCellValue();  //获取单元格数据
            userCandidate.setExpectSalaryMax(Integer.valueOf(maxsalay));

            db.insert(userCandidate);

            System.out.println(userData);
        }
        if (uploadResult.getSuccess()) {
            System.out.println(uploadResult.getUploadUriPath());
            return ActionResult.ok(true);
        } else {
            return ActionResult.error(uploadResult.getMsg());
        }
    }
}
