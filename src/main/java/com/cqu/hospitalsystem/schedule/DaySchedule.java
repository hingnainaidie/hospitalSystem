package com.cqu.hospitalsystem.schedule;

import com.cqu.hospitalsystem.dao.RegistrationDao;
import com.cqu.hospitalsystem.entity.Registration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@Configuration
@EnableScheduling   //开启定时任务
public class DaySchedule {
    @Resource
    RegistrationDao registrationDao;

    //每日零点刷新registration的状态
    @Scheduled(cron = "0 0 0 * * ?")
    private void regDayUpdate(){
        List<Registration> registrations=registrationDao.queryAll();
        for(Registration reg:registrations){
            Date date = new Date(System.currentTimeMillis());
            int compareTo=date.compareTo(reg.getAppointmentTime());
            if(compareTo>=0){
                Registration registration=new Registration();
                registration.setRegId(reg.getRegId());
                registration.setState(5);
                registrationDao.update(registration);
            }
        }
    }

    //每20分钟刷新自动取消未缴费预约
    @Scheduled(fixedRate = 20*60*1000)
    private void regCancel(){
        List<Registration> registrations=registrationDao.queryAll();
        for(Registration reg:registrations){
            Date date = new Date(System.currentTimeMillis());
            int compareTo=date.compareTo(reg.getAppointmentTime());
            if(compareTo>=0&&reg.getState()==0){
                registrationDao.deleteById(reg.getRegId());
            }
        }
    }
}
