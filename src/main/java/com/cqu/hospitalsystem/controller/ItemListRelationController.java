package com.cqu.hospitalsystem.controller;

import com.cqu.hospitalsystem.entity.ItemListRelation;
import com.cqu.hospitalsystem.service.ItemListRelationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ItemListRelation)表控制层
 *
 * @author makejava
 * @since 2021-08-28 16:59:59
 */
@RestController
@RequestMapping("itemListRelation")
public class ItemListRelationController {
    /**
     * 服务对象
     */
    @Resource
    private ItemListRelationService itemListRelationService;

    /**
     * 分页查询
     *
     * @param itemListRelation 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ItemListRelation>> queryByPage(ItemListRelation itemListRelation, PageRequest pageRequest) {
        return ResponseEntity.ok(this.itemListRelationService.queryByPage(itemListRelation, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ItemListRelation> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.itemListRelationService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param itemListRelation 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ItemListRelation> add(ItemListRelation itemListRelation) {
        return ResponseEntity.ok(this.itemListRelationService.insert(itemListRelation));
    }

    /**
     * 编辑数据
     *
     * @param itemListRelation 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ItemListRelation> edit(ItemListRelation itemListRelation) {
        return ResponseEntity.ok(this.itemListRelationService.update(itemListRelation));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.itemListRelationService.deleteById(id));
    }

}

