package com.puzhibing.trialtask.pojo.zhuanke;

import java.util.List;

public class Body {

	private List<Tasks> List;
	
	private String Page;
	
	private String RecordNum;
	
	private String isDownNum;
	
	private String returnCode;
	
	private String listCountDownTime;

	public List<Tasks> getList() {
		return List;
	}

	public void setList(List<Tasks> list) {
		List = list;
	}

	public String getPage() {
		return Page;
	}

	public void setPage(String page) {
		Page = page;
	}

	public String getRecordNum() {
		return RecordNum;
	}

	public void setRecordNum(String recordNum) {
		RecordNum = recordNum;
	}

	public String getIsDownNum() {
		return isDownNum;
	}

	public void setIsDownNum(String isDownNum) {
		this.isDownNum = isDownNum;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getListCountDownTime() {
		return listCountDownTime;
	}

	public void setListCountDownTime(String listCountDownTime) {
		this.listCountDownTime = listCountDownTime;
	}

	@Override
	public String toString() {
		return "Body [List=" + List + ", Page=" + Page + ", RecordNum=" + RecordNum + ", isDownNum=" + isDownNum
				+ ", returnCode=" + returnCode + ", listCountDownTime=" + listCountDownTime + "]";
	}
	
	
}
