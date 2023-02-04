package com.cqu.hospitalsystem.entity;

import java.io.Serializable;

/**
 * (Medicine)实体类
 *
 * @author makejava
 * @since 2021-08-28 17:00:37
 */
public class Medicine implements Serializable {
    private static final long serialVersionUID = -61309650103831692L;
    
    private Long medicineId;
    
    private String medicineName;
    
    private String specification;
    
    private String frequency;
    
    private String dosagePer;
    
    private Double pricePer;


    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDosagePer() {
        return dosagePer;
    }

    public void setDosagePer(String dosagePer) {
        this.dosagePer = dosagePer;
    }

    public Double getPricePer() {
        return pricePer;
    }

    public void setPricePer(Double pricePer) {
        this.pricePer = pricePer;
    }

}

