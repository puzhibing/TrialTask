package com.puzhibing.trialtask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puzhibing.trialtask.service.IHttpRequestService;
import com.puzhibing.trialtask.util.HttpRequestUtil;

@Service
public class HttpRequestServiceImpl implements IHttpRequestService {
	
	@Autowired
	private HttpRequestUtil httpRequestUtil;

	
	/*
	 * 对PP红包请求的处理函数
	 * @see com.puzhibing.testPlayAid.service.IHttpRequestService#httpRequestPP(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("static-access")
	@Override
	public String httpRequestPP(String RequestUrl, String parameters) {
		String resouce = httpRequestUtil.sendGet(RequestUrl, parameters);
		return resouce;
	}

}
