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

    private String url = "http://shike.com/shike/api/appList";
    private String download = "1";
    private String asin = "qLgKdimlcEzTNHiZTdJvd%2B%2B%2B6GQQz9WlpjYDe%2BM4%2BZ87GVf%2FYJRdh%2FFUKDzSv4rvkFDGwzFW3oz6B4YSaF6LMNQtUCBh8Suiw49XR4iOIwQBinKX%2BUjSWUffZ7dSTK3S3WgHB2VyLSt5d9yzHx%2B%2BrnkXCfAaW27%2BnCc52w%2FR7eoIHt2rB0zyT6fkilXYlpwMXGL%2BGhfxkoxGjmVC%2BvqGYMuSjjyvN5z5yXyxH2GZ%2F%2F4DBSpKZTXIL2P76ui1wzFL2UFJ0EB9HnQPdxb8Y6NSQA%3D%3D";


    @Scheduled(cron = "0/5 * 9-23 * * *")
    public void scheduledShike(){
        IShikeAppServiceImpl.getTaskList(url , download , asin);
    }
    
    
    @Scheduled(cron = "0/5 * 9-23 * * *")
    public void scheduledXiaozhu(){
    	xiaoZhuServiceImpl.getTaskList();
    }

}
