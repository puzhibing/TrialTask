package com.puzhibing.trialtask.service;

public interface IHttpRequestService {

	
	/**
	 * 对PP红包的请求
	 * @param RequestUrl
	 * @param parameters
	 * @return
	 */
	public String httpRequestPP(String RequestUrl , String parameters);
}
