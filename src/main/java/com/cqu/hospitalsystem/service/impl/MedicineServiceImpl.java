package com.cqu.hospitalsystem.service.impl;

import com.cqu.hospitalsystem.Vo.preMedicineVo;
import com.cqu.hospitalsystem.entity.Medicine;
import com.cqu.hospitalsystem.dao.MedicineDao;
import com.cqu.hospitalsystem.service.MedicineService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Medicine)表服务实现类
 *
 * @author makejava
 * @since 2021-08-28 17:00:37
 */
@Service("medicineService")
public class MedicineServiceImpl implements MedicineService {
    @Resource
    private MedicineDao medicineDao;

    /**
     * 通过ID查询单条数据
     *
     * @param medicineId 主键
     * @return 实例对象
     */
    @Override
    public Medicine queryById(Long medicineId) {
        return this.medicineDao.queryById(medicineId);
    }
    @Override
    public List<Medicine> allMedicine() {
        return this.medicineDao.allMedicine();
    }

    /**
     * 分页查询
     *
     * @param medicine 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Medicine> queryByPage(Medicine medicine, PageRequest pageRequest) {
        long total = this.medicineDao.count(medicine);
        return new PageImpl<>(this.medicineDao.queryAllByLimit(medicine, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param medicine 实例对象
     * @return 实例对象
     */
    @Override
    public Medicine insert(Medicine medicine) {
        this.medicineDao.insert(medicine);
        return medicine;
    }

    /**
     * 修改数据
     *
     * @param medicine 实例对象
     * @return 实例对象
     */
    @Override
    public Medicine update(Medicine medicine) {
        this.medicineDao.update(medicine);
        return this.queryById(medicine.getMedicineId());
    }

    /**
     * 通过主键删除数据
     *
     * @param medicineId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long medicineId) {
        return this.medicineDao.deleteById(medicineId) > 0;
    }

    /**
     * 退药
     * @param preMedicineVo
     * @return
     */
    @Override
    public boolean cancelMedicine(preMedicineVo preMedicineVo) {
        return this.medicineDao.cancelMedicine(preMedicineVo);
    }
}
