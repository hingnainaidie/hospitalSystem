package com.cqu.hospitalsystem.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Checklist)实体类
 *
 * @author makejava
 * @since 2021-08-28 16:58:52
 */
public class Checklist implements Serializable {
    private static final long serialVersionUID = 522251215308050462L;
    
    private Long checklistId;
    
    private Date prescribeTime;
    /**
     * 0：未交费，1：已缴费
     */
    private Integer checklistStatus;


    public Long getChecklistId() {
        return checklistId;
    }

    public void setChecklistId(Long checklistId) {
        this.checklistId = checklistId;
    }

    public Date getPrescribeTime() {
        return prescribeTime;
    }

    public void setPrescribeTime(Date prescribeTime) {
        this.prescribeTime = prescribeTime;
    }

    public Integer getChecklistStatus() {
        return checklistStatus;
    }

    public void setChecklistStatus(Integer checklistStatus) {
        this.checklistStatus = checklistStatus;
    }

}

