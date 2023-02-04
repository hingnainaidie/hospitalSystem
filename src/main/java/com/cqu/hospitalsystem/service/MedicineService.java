package com.cqu.hospitalsystem.service;

import com.cqu.hospitalsystem.Vo.preMedicineVo;
import com.cqu.hospitalsystem.entity.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Medicine)表服务接口
 *
 * @author makejava
 * @since 2021-08-28 17:00:37
 */
public interface MedicineService {

    /**
     * 通过ID查询单条数据
     *
     * @param medicineId 主键
     * @return 实例对象
     */
    Medicine queryById(Long medicineId);
    List<Medicine> allMedicine();
    /**
     * 分页查询
     *
     * @param medicine 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Medicine> queryByPage(Medicine medicine, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param medicine 实例对象
     * @return 实例对象
     */
    Medicine insert(Medicine medicine);

    /**
     * 修改数据
     *
     * @param medicine 实例对象
     * @return 实例对象
     */
    Medicine update(Medicine medicine);

    /**
     * 通过主键删除数据
     *
     * @param medicineId 主键
     * @return 是否成功
     */
    boolean deleteById(Long medicineId);

    /**
     * 退药
     * @param preMedicineVo
     * @return
     */
    boolean cancelMedicine(preMedicineVo preMedicineVo);
}
