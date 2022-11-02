package com.shopping.wx.model;

import javax.persistence.*;

@Table(name = "district")
public class District {
    @Id
    private String id;

    @Column(name = "district_name")
    private String districtName;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return district_name
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * @param districtName
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }
}