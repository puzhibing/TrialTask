package com.puzhibing.trialtask.util;

import com.puzhibing.trialtask.service.IShikeAppService;
import com.puzhibing.trialtask.service.XiaoZhuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Async
@Component
public class ScheduledService {

    @Autowired
    private IShikeAppService IShikeAppServiceImpl;
    
    @Autowired
	private XiaoZhuService xiaoZhuServiceImpl;


    @Scheduled(cron = "0/3 * 9-23 * * *")
    public void scheduledShike(){
        System.out.println("shike.......");
        IShikeAppServiceImpl.getTaskList();
    }
    
    
    @Scheduled(cron = "1/3 * 9-23 * * *")
    public void scheduledXiaozhu(){
        System.out.println("xiaozhu.......");
    	xiaoZhuServiceImpl.getTaskList();
    }

}
