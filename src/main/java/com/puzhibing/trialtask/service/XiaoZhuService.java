package com.puzhibing.trialtask.service;

import com.puzhibing.trialtask.util.ResultUtil;

public interface XiaoZhuService {

	
	ResultUtil getTaskList() throws Exception;


	/**
	 * 获取验证码
	 * @return
	 */
	boolean getVerificationCode();


	/**
	 * 输入验证码登录
	 * @param vc
	 * @return
	 */
	boolean verifyLogin(String vc);
}
