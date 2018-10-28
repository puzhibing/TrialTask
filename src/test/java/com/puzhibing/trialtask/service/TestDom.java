package com.puzhibing.trialtask.service;

import org.junit.Test;

import com.puzhibing.trialtask.service.impl.RehuluServiceImpl;
import com.puzhibing.trialtask.service.impl.XiaoZhuServiceImpl;

public class TestDom {
	
	
	
	@Test
	public void test() {
//		XiaoZhuService xiaoZhuServiceImpl = new XiaoZhuServiceImpl();
//		xiaoZhuServiceImpl.getTaskList();
		
		RehuluService rehuluService = new RehuluServiceImpl();
		rehuluService.loginHome();
	}

}
