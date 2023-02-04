package com.cqu.hospitalsystem.entity;

import java.io.Serializable;

/**
 * (IdRelation)实体类
 *
 * @author makejava
 * @since 2021-08-28 16:59:40
 */
public class IdRelation implements Serializable {
    private static final long serialVersionUID = -23719944733264204L;
    /**
     * 主键
     */
    private Long idRelationId;
    /**
     * 挂号
     */
    private Long regId;
    /**
     * 病历
     */
    private Long recordId;
    /**
     * 处方
     */
    private Long prescriptionId;
    /**
     * 检查单
     */
    private Long checklistId;


    public Long getIdRelationId() {
        return idRelationId;
    }

    public void setIdRelationId(Long idRelationId) {
        this.idRelationId = idRelationId;
    }

    public Long getRegId() {
        return regId;
    }

    public void setRegId(Long regId) {
        this.regId = regId;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Long getChecklistId() {
        return checklistId;
    }

    public void setChecklistId(Long checklistId) {
        this.checklistId = checklistId;
    }

}

