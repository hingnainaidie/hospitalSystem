package com.cqu.hospitalsystem.entity;

import java.io.Serializable;

/**
 * (Checkitem)实体类
 *
 * @author makejava
 * @since 2021-08-28 16:58:20
 */
public class Checkitem implements Serializable {
    private static final long serialVersionUID = -87256015869586418L;
    
    private Long checkitemId;
    
    private Long officeId;
    
    private String checkitemName;
    
    private String checkPart;
    
    private Double checkitemPrice;
    
    private String checkitemNote;


    public Long getCheckitemId() {
        return checkitemId;
    }

    public void setCheckitemId(Long checkitemId) {
        this.checkitemId = checkitemId;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getCheckitemName() {
        return checkitemName;
    }

    public void setCheckitemName(String checkitemName) {
        this.checkitemName = checkitemName;
    }

    public String getCheckPart() {
        return checkPart;
    }

    public void setCheckPart(String checkPart) {
        this.checkPart = checkPart;
    }

    public Double getCheckitemPrice() {
        return checkitemPrice;
    }

    public void setCheckitemPrice(Double checkitemPrice) {
        this.checkitemPrice = checkitemPrice;
    }

    public String getCheckitemNote() {
        return checkitemNote;
    }

    public void setCheckitemNote(String checkitemNote) {
        this.checkitemNote = checkitemNote;
    }

}

