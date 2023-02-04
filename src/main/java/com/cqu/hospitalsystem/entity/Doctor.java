package com.cqu.hospitalsystem.entity;

import java.io.Serializable;

/**
 * (Doctor)实体类
 *
 * @author makejava
 * @since 2021-08-28 16:59:22
 */
public class Doctor implements Serializable {
    private static final long serialVersionUID = 356169716564627791L;
    
    private Long docId;
    
    private String docName;
    
    private String docPassword;
    
    private String docGender;
    
    private Long docIdentify;
    
    private String docPhone;
    
    private Integer docAge;
    
    private String docLevel;
    
    private Long officeId;
    
    private Integer docPrice;
    
    private Long maxPatient;
    
    private Long currentPatient;
    
    private Integer classifcation;


    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocPassword() {
        return docPassword;
    }

    public void setDocPassword(String docPassword) {
        this.docPassword = docPassword;
    }

    public String getDocGender() {
        return docGender;
    }

    public void setDocGender(String docGender) {
        this.docGender = docGender;
    }

    public Long getDocIdentify() {
        return docIdentify;
    }

    public void setDocIdentify(Long docIdentify) {
        this.docIdentify = docIdentify;
    }

    public String getDocPhone() {
        return docPhone;
    }

    public void setDocPhone(String docPhone) {
        this.docPhone = docPhone;
    }

    public Integer getDocAge() {
        return docAge;
    }

    public void setDocAge(Integer docAge) {
        this.docAge = docAge;
    }

    public String getDocLevel() {
        return docLevel;
    }

    public void setDocLevel(String docLevel) {
        this.docLevel = docLevel;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public Integer getDocPrice() {
        return docPrice;
    }

    public void setDocPrice(Integer docPrice) {
        this.docPrice = docPrice;
    }

    public Long getMaxPatient() {
        return maxPatient;
    }

    public void setMaxPatient(Long maxPatient) {
        this.maxPatient = maxPatient;
    }

    public Long getCurrentPatient() {
        return currentPatient;
    }

    public void setCurrentPatient(Long currentPatient) {
        this.currentPatient = currentPatient;
    }

    public Integer getClassifcation() {
        return classifcation;
    }

    public void setClassifcation(Integer classifcation) {
        this.classifcation = classifcation;
    }

}

