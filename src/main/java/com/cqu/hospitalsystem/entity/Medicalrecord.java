package com.cqu.hospitalsystem.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Medicalrecord)实体类
 *
 * @author makejava
 * @since 2021-08-28 17:00:15
 */
public class Medicalrecord implements Serializable {
    private static final long serialVersionUID = 380696534714406566L;
    public Medicalrecord(String recordType, Date illTime, String recordResult, String allergy, String presentIllness){
        this.recordType=recordType;
        this.allergy=allergy;
        this.recordResult=recordResult;
        this.illTime=illTime;
        this.presentIllness=presentIllness;
        this.mrecordId=mrecordId;
    }
    
    private Long mrecordId;
    
    private String recordType;
    
    private Date illTime;
    
    private String recordResult;
    
    private String allergy;
    
    private String presentIllness;


    public Long getMrecordId() {
        return mrecordId;
    }

    public void setMrecordId(Long mrecordId) {
        this.mrecordId = mrecordId;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public Date getIllTime() {
        return illTime;
    }

    public void setIllTime(Date illTime) {
        this.illTime = illTime;
    }

    public String getRecordResult() {
        return recordResult;
    }

    public void setRecordResult(String recordResult) {
        this.recordResult = recordResult;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getPresentIllness() {
        return presentIllness;
    }

    public void setPresentIllness(String presentIllness) {
        this.presentIllness = presentIllness;
    }

}

