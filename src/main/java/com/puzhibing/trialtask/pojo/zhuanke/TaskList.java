package com.puzhibing.trialtask.pojo.zhuanke;

public class TaskList {

	private String protocol;
	
	private String flag;
	
	private String time;
	
	private Message message;
	
	private Body body;

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "TaskList [protocol=" + protocol + ", flag=" + flag + ", time=" + time + ", message=" + message
				+ ", body=" + body + "]";
	}
	
	
}
