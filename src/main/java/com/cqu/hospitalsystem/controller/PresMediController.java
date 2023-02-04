package com.cqu.hospitalsystem.controller;

import com.cqu.hospitalsystem.entity.PresMedi;
import com.cqu.hospitalsystem.service.PresMediService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (PresMedi)表控制层
 *
 * @author makejava
 * @since 2021-08-28 17:01:29
 */
@RestController
@RequestMapping("presMedi")
public class PresMediController {
    /**
     * 服务对象
     */
    @Resource
    private PresMediService presMediService;

    /**
     * 分页查询
     *
     * @param presMedi 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<PresMedi>> queryByPage(PresMedi presMedi, PageRequest pageRequest) {
        return ResponseEntity.ok(this.presMediService.queryByPage(presMedi, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<PresMedi> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.presMediService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param presMedi 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<PresMedi> add(PresMedi presMedi) {
        return ResponseEntity.ok(this.presMediService.insert(presMedi));
    }

    /**
     * 编辑数据
     *
     * @param presMedi 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<PresMedi> edit(PresMedi presMedi) {
        return ResponseEntity.ok(this.presMediService.update(presMedi));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.presMediService.deleteById(id));
    }

}

