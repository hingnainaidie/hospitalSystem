package com.cqu.hospitalsystem.entity;

import java.io.Serializable;

/**
 * (Office)实体类
 *
 * @author makejava
 * @since 2021-08-28 17:00:57
 */
public class Office implements Serializable {
    private static final long serialVersionUID = -72640397629161631L;
    
    private Long officeId;
    
    private String officeName;


    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

}

