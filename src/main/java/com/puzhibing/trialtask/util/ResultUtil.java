package com.puzhibing.trialtask.util;

/**
 * 定义结果对象
 * @author asus
 *
 */
public class ResultUtil {
	
	private boolean status;
	
	private String img;
	
	private String msg;
	
	private String info;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "ResultUtil [status=" + status + ", img=" + img + ", msg=" + msg + ", info=" + info + "]";
	}

	
	
	
	
}
