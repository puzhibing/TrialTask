package com.puzhibing.trialtask.pojo.shike;

public class PreList {

	private String sign;
	
	private String searchWord;
	
	private int number;
	
	private String money;
	
	private boolean today;
	
	private int appType;
	
	private long startTime;
	
	private int access;
	
	private String tid;
	
	private String trackId;
	
	private boolean remind;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public boolean isToday() {
		return today;
	}

	public void setToday(boolean today) {
		this.today = today;
	}

	public int getAppType() {
		return appType;
	}

	public void setAppType(int appType) {
		this.appType = appType;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

	public boolean isRemind() {
		return remind;
	}

	public void setRemind(boolean remind) {
		this.remind = remind;
	}

	@Override
	public String toString() {
		return "PreList [sign=" + sign + ", searchWord=" + searchWord + ", number=" + number + ", money=" + money
				+ ", today=" + today + ", appType=" + appType + ", startTime=" + startTime + ", access=" + access
				+ ", tid=" + tid + ", trackId=" + trackId + ", remind=" + remind + "]";
	}
	
	
}
