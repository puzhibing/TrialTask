package com.puzhibing.trialtask.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puzhibing.trialtask.service.IShikeAppService;
import com.puzhibing.trialtask.service.XiaoZhuService;
import com.puzhibing.trialtask.util.ComUtil;
import com.puzhibing.trialtask.util.ResultUtil;

/**
 * 试客APP的接口类
 * @author asus
 *
 */
@RestController
public class AppController {

	@Autowired
	private IShikeAppService iShikeAppServiceImpl;
	
	@Autowired
	private XiaoZhuService xiaoZhuServiceImpl;
	
	@Autowired
	private ComUtil comUtil;
	
	
	/**
	 * 获取抢成功的任务数据
	 * @return
	 */
	@RequestMapping(value = "/getTaskList")
	public List<ResultUtil> getTaskList() {
		List<ResultUtil> list = new ArrayList<>();
		String url = "http://shike.com/shike/api/appList";
		String download = "1";
		String asin = "qLgKdimlcEzTNHiZTdJvd%2B%2B%2B6GQQz9WlpjYDe%2BM4%2BZ87GVf%2FYJRdh%2FFUKDzSv4rvkFDGwzFW3oz6B4YSaF6LMNQtUCBh8Suiw49XR4iOIwQBinKX%2BUjSWUffZ7dSTK3S3WgHB2VyLSt5d9yzHx%2B%2BrnkXCfAaW27%2BnCc52w%2FR7eoIHt2rB0zyT6fkilXYlpwMXGL%2BGhfxkoxGjmVC%2BvqGYMuSjjyvN5z5yXyxH2GZ%2F%2F4DBSpKZTXIL2P76ui1wzFL2UFJ0EB9HnQPdxb8Y6NSQA%3D%3D";
		ResultUtil shike = iShikeAppServiceImpl.getTaskList(url, download, asin);
		list.add(shike);
		
		ResultUtil xiaozhu = xiaoZhuServiceImpl.getTaskList();
		list.add(xiaozhu);
		
		return list;
	}
	
	
	
	/**
	 * 放弃当前任务
	 * @param sign
	 * @return
	 */
	@RequestMapping(value = "/giveUpTask")
	public ResultUtil giveUpTask(String appType , String identifier) {
		comUtil.putValueInMap(appType, identifier);
		ResultUtil resultUtil = new ResultUtil();
		resultUtil.setStatus(true);
		return resultUtil;
	}
}
