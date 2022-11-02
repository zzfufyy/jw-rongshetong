package com.shopping.wx.util_service;

import com.shopping.wx.constant.AuditConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BuildingNameListBuilder
 * @Description 楼栋名字builder  必须提供构造参数
 * @Author zyw
 * @Date 2022/4/16
 **/
public class BuildingNameListBuilder {
    private int buildingNameType;
    private int num;
    private static final int MAX_ENGLISH_NAME_NUM = 26;
    private static final String SUFFIX_BUILDING_NAME = "栋";
    private List<String> buildingNameList;

    public BuildingNameListBuilder(int buildingNameType, int num) {
        this.buildingNameType = buildingNameType;
        this.num = num;
        this.buildingNameList = new ArrayList<>(num);
    }

    public List<String> build() {
        if (buildingNameType == AuditConstant.BuildingNameType.NumberType.getValue()) {
            for (int i = 0; i < this.num; i++) {
                buildingNameList.add(String.valueOf(i + 1) + SUFFIX_BUILDING_NAME);
            }
        } else if (buildingNameType == AuditConstant.BuildingNameType.EnglishType.getValue()) {
            if (this.num > MAX_ENGLISH_NAME_NUM) {
                throw new RuntimeException("Max English Building Name length is not ALLOWED over " + MAX_ENGLISH_NAME_NUM);
            }
            for (int i = 0; i < this.num; i++) {
                buildingNameList.add(String.valueOf((char) ('A' + i)) + SUFFIX_BUILDING_NAME);
            }
        }
        return this.buildingNameList;
    }

    public static String buildBuildingName(int buildingNameType, int buildingOrder) {
        if (buildingNameType == AuditConstant.BuildingNameType.NumberType.getValue()) {
            return String.valueOf(buildingOrder) + SUFFIX_BUILDING_NAME;
        } else if (buildingNameType == AuditConstant.BuildingNameType.EnglishType.getValue()) {
            return String.valueOf((char) ('A' + (buildingOrder - 1)) + SUFFIX_BUILDING_NAME);
        }
        return null;
    }
}
