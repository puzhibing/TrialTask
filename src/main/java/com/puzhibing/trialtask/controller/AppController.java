package com.puzhibing.trialtask.controller;

import java.util.ArrayList;
import java.util.List;

import com.puzhibing.trialtask.util.ResourceUtil;
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
		ResultUtil shike = iShikeAppServiceImpl.getTaskList();
		list.add(shike);

		ResultUtil xiaozhu = null;
		try {
			xiaozhu = xiaoZhuServiceImpl.getTaskList();
		} catch (Exception e) {
			xiaozhu = new ResultUtil();
			xiaozhu.setStatus(false);
			xiaozhu.setMsg("sesionid过期，请重新获取");
			e.printStackTrace();
		}
		list.add(xiaozhu);
		
		return list;
	}
	
	
	
	/**
	 * 放弃当前任务
	 * @param appType
	 * @param identifier
	 * @return
	 */
	@RequestMapping(value = "/giveUpTask")
	public ResultUtil giveUpTask(String appType , String identifier) {
		comUtil.putValueInMap(appType, identifier);
		ResultUtil resultUtil = new ResultUtil();
		resultUtil.setStatus(true);
		return resultUtil;
	}


	/**
	 * 添加配置文件内容
	 * @param key
	 * @param value
	 */
	@RequestMapping(value = "/setValue")
	public boolean setValue(String key , String value){
		boolean b = false;
		try {
			ResourceUtil.getResourceUtil("parameter.txt").setValue(key , value);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

}
