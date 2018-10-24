package com.puzhibing.trialtask.pojo.zhuanke;

public class Message {

	private String code;
	
	private String content;
	
	private String codeFlag;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCodeFlag() {
		return codeFlag;
	}

	public void setCodeFlag(String codeFlag) {
		this.codeFlag = codeFlag;
	}

	@Override
	public String toString() {
		return "Message [code=" + code + ", content=" + content + ", codeFlag=" + codeFlag + "]";
	}
	
	
}
