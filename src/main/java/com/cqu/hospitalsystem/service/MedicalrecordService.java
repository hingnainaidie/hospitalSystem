package com.cqu.hospitalsystem.service;

import com.cqu.hospitalsystem.Vo.Reg_record;
import com.cqu.hospitalsystem.entity.Medicalrecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Medicalrecord)表服务接口
 *
 * @author makejava
 * @since 2021-08-28 17:00:15
 */
public interface MedicalrecordService {
    boolean saveRecord(Reg_record reg_record);
    /**
     * 通过ID查询单条数据
     *
     * @param mrecordId 主键
     * @return 实例对象
     */
    Medicalrecord queryById(Long mrecordId);

    /**
     * 分页查询
     *
     * @param medicalrecord 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Medicalrecord> queryByPage(Medicalrecord medicalrecord, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param medicalrecord 实例对象
     * @return 实例对象
     */
    Medicalrecord insert(Medicalrecord medicalrecord);

    /**
     * 修改数据
     *
     * @param medicalrecord 实例对象
     * @return 实例对象
     */
    Medicalrecord update(Medicalrecord medicalrecord);

    /**
     * 通过主键删除数据
     *
     * @param mrecordId 主键
     * @return 是否成功
     */
    boolean deleteById(Long mrecordId);

}
