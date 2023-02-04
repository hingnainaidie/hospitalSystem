package com.cqu.hospitalsystem.cache;

import com.cqu.hospitalsystem.Vo.RegSepout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "queues")
public class CacheService {
    @Autowired
    private CacheRepository cacheRepository;

    //获取缓存的对应医生队列信息
    public List<RegSepout> getQueue(Long doc_id){
        return cacheRepository.getQueue(doc_id);
    }
    //新增队列信息
    @CachePut(key = "#reg_id")
    public List<RegSepout> push(Long reg_id){
        return cacheRepository.push(reg_id);
    }
    //新增队列信息
    @CachePut(key = "#reg_id")
    public List<RegSepout> pushInsert(Long reg_id){
        return cacheRepository.pushInsert(reg_id);
    }
    //出队
    @CachePut(key = "#doc_id")
    public List<RegSepout> poll(Long doc_id){
        return cacheRepository.poll(doc_id);
    }

    //打印所有队列
    public void printMap(){
        cacheRepository.printList();
    }
    //获取当前预约的排队序号
    public int getQueueNum(Long patient_id){
        return cacheRepository.getQueueNum(patient_id);
    }
    //获取病人对应的reg_id信息
    public Long getSeqReg_id(Long patient_id){
        return cacheRepository.getSeqReg_id(patient_id);
    }
    @CachePut(key = "#reg_id")
    public List<RegSepout> unhang(Long reg_id){
        return cacheRepository.unhang(reg_id);
    }
}
