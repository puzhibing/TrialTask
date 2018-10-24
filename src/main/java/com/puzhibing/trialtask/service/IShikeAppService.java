package com.puzhibing.trialtask.service;

import com.puzhibing.trialtask.util.ResultUtil;

/**
 * 定义操作shike APP系统接口
 * @author asus
 *
 */
public interface IShikeAppService {

	/**
	 * 定义通过get请求获取任务列表
	 * @return
	 */
	public ResultUtil getTaskList(String url, String download , String asin);
}
