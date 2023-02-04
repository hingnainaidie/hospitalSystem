package com.cqu.hospitalsystem.entity;

import java.io.Serializable;

/**
 * (Recordtemplate)实体类
 *
 * @author makejava
 * @since 2021-08-28 17:02:04
 */
public class Recordtemplate implements Serializable {
    private static final long serialVersionUID = -96925943929922853L;
    
    private Long rtemplateId;
    
    private String templateName;
    
    private String templateResult;


    public Long getRtemplateId() {
        return rtemplateId;
    }

    public void setRtemplateId(Long rtemplateId) {
        this.rtemplateId = rtemplateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateResult() {
        return templateResult;
    }

    public void setTemplateResult(String templateResult) {
        this.templateResult = templateResult;
    }

}

