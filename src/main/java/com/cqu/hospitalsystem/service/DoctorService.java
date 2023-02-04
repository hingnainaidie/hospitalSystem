package com.cqu.hospitalsystem.service;

import com.cqu.hospitalsystem.Vo.DocOffice;
import com.cqu.hospitalsystem.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

/**
 * (Doctor)表服务接口
 *
 * @author makejava
 * @since 2021-08-28 16:59:22
 */
public interface DoctorService {
    Doctor loginDoctor(Doctor d);
    boolean editDocPwd(Doctor d);
    boolean editDocInfo(Doctor d);
    List<Doctor> searchAvailable(Long officeId, Date appointTime);
    /**
     * 通过ID查询单条数据
     *
     * @param docId 主键
     * @return 实例对象
     */
    Doctor queryById(Long docId);

    /**
     * 分页查询
     *
     * @param doctor 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Doctor> queryByPage(Doctor doctor, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param doctor 实例对象
     * @return 实例对象
     */
    Doctor insert(Doctor doctor);

    /**
     * 修改数据
     *
     * @param doctor 实例对象
     * @return 实例对象
     */
    Doctor update(Doctor doctor);

    /**
     * 通过主键删除数据
     *
     * @param docId 主键
     * @return 是否成功
     */
    boolean deleteById(Long docId);

    List<DocOffice> searchDocList(String office_name);

    DocOffice docDetail(Long doc_id);
}
