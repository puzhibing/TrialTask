package com.puzhibing.trialtask.pojo.shike;

import java.util.List;

/**
 * 定义返回的封装对象
 * @author asus
 *
 */
public class ResultList {

private String code;
	
	private String desc;
	
	private List<TaskList> data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<TaskList> getData() {
		return data;
	}

	public void setData(List<TaskList> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultList [code=" + code + ", desc=" + desc + ", data=" + data + "]";
	}

	
}
