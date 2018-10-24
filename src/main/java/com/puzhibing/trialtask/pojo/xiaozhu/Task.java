package com.puzhibing.trialtask.pojo.xiaozhu;

public class Task {

	private String uid;
	
	private String missionid;
	
	private String bundleid;
	
	private String tag;
	
	private String reward;
	
	private String appid;
	
	private String img;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getMissionid() {
		return missionid;
	}

	public void setMissionid(String missionid) {
		this.missionid = missionid;
	}

	public String getBundleid() {
		return bundleid;
	}

	public void setBundleid(String bundleid) {
		this.bundleid = bundleid;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Task [uid=" + uid + ", missionid=" + missionid + ", bundleid=" + bundleid + ", tag=" + tag + ", reward="
				+ reward + ", appid=" + appid + ", img=" + img + "]";
	}

	

	

	
	
}
