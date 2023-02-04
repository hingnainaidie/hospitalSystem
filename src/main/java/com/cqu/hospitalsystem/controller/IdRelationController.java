package com.cqu.hospitalsystem.controller;

import com.cqu.hospitalsystem.entity.IdRelation;
import com.cqu.hospitalsystem.service.IdRelationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (IdRelation)表控制层
 *
 * @author makejava
 * @since 2021-08-28 16:59:40
 */
@RestController
@RequestMapping("idRelation")
public class IdRelationController {
    /**
     * 服务对象
     */
    @Resource
    private IdRelationService idRelationService;

    /**
     * 分页查询
     *
     * @param idRelation 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<IdRelation>> queryByPage(IdRelation idRelation, PageRequest pageRequest) {
        return ResponseEntity.ok(this.idRelationService.queryByPage(idRelation, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<IdRelation> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.idRelationService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param idRelation 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<IdRelation> add(IdRelation idRelation) {
        return ResponseEntity.ok(this.idRelationService.insert(idRelation));
    }

    /**
     * 编辑数据
     *
     * @param idRelation 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<IdRelation> edit(IdRelation idRelation) {
        return ResponseEntity.ok(this.idRelationService.update(idRelation));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.idRelationService.deleteById(id));
    }

}

