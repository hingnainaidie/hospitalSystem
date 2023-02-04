package com.cqu.hospitalsystem.service.impl;

import com.cqu.hospitalsystem.entity.PresMedi;
import com.cqu.hospitalsystem.dao.PresMediDao;
import com.cqu.hospitalsystem.service.PresMediService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (PresMedi)表服务实现类
 *
 * @author makejava
 * @since 2021-08-28 17:01:29
 */
@Service("presMediService")
public class PresMediServiceImpl implements PresMediService {
    @Resource
    private PresMediDao presMediDao;

    /**
     * 通过ID查询单条数据
     *
     * @param connectId 主键
     * @return 实例对象
     */
    @Override
    public PresMedi queryById(Long connectId) {
        return this.presMediDao.queryById(connectId);
    }

    /**
     * 分页查询
     *
     * @param presMedi 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<PresMedi> queryByPage(PresMedi presMedi, PageRequest pageRequest) {
        long total = this.presMediDao.count(presMedi);
        return new PageImpl<>(this.presMediDao.queryAllByLimit(presMedi, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param presMedi 实例对象
     * @return 实例对象
     */
    @Override
    public PresMedi insert(PresMedi presMedi) {
        this.presMediDao.insert(presMedi);
        return presMedi;
    }

    /**
     * 修改数据
     *
     * @param presMedi 实例对象
     * @return 实例对象
     */
    @Override
    public PresMedi update(PresMedi presMedi) {
        this.presMediDao.update(presMedi);
        return this.queryById(presMedi.getConnectId());
    }

    /**
     * 通过主键删除数据
     *
     * @param connectId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long connectId) {
        return this.presMediDao.deleteById(connectId) > 0;
    }
}
