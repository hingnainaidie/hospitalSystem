package com.cqu.hospitalsystem.entity;

import java.io.Serializable;

/**
 * (ItemListRelation)实体类
 *
 * @author makejava
 * @since 2021-08-28 16:59:59
 */
public class ItemListRelation implements Serializable {
    private static final long serialVersionUID = -89278307363368998L;
    
    private Long itemListId;
    
    private Long checkitemId;
    
    private Long checklistId;
    
    private Integer state;


    public Long getItemListId() {
        return itemListId;
    }

    public void setItemListId(Long itemListId) {
        this.itemListId = itemListId;
    }

    public Long getCheckitemId() {
        return checkitemId;
    }

    public void setCheckitemId(Long checkitemId) {
        this.checkitemId = checkitemId;
    }

    public Long getChecklistId() {
        return checklistId;
    }

    public void setChecklistId(Long checklistId) {
        this.checklistId = checklistId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}

