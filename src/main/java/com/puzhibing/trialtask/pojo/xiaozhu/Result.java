package com.puzhibing.trialtask.pojo.xiaozhu;

public class Result {

	private String mas;
	
	private String success;
	
	private int ret;

	public String getMas() {
		return mas;
	}

	public void setMas(String mas) {
		this.mas = mas;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	@Override
	public String toString() {
		return "Result [mas=" + mas + ", success=" + success + ", ret=" + ret + "]";
	}
	
	
}
