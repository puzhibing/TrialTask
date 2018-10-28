package com.puzhibing.trialtask.pojo.shike;

import java.util.List;

/**
 * 定义试客任务列表对象类
 * @author asus
 *
 */
public class TaskList {

	private List<App> app;
	
	private boolean update;
	
	private String updateUrl;
	
	private int times;
	
	private boolean showCoupon;
	
	private String subscribeUrl;
	
	private int qqComicInstall;
	
	private int shuqiInstall;
	
	private List<PreList> preList;
	
	private List<AccPreList> accPreList;
	
	private List<RedList> redList;
	
	private boolean showRemind;

	public List<App> getApp() {
		return app;
	}

	public void setApp(List<App> app) {
		this.app = app;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public String getUpdateUrl() {
		return updateUrl;
	}

	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public boolean isShowCoupon() {
		return showCoupon;
	}

	public void setShowCoupon(boolean showCoupon) {
		this.showCoupon = showCoupon;
	}

	public String getSubscribeUrl() {
		return subscribeUrl;
	}

	public void setSubscribeUrl(String subscribeUrl) {
		this.subscribeUrl = subscribeUrl;
	}

	public int getQqComicInstall() {
		return qqComicInstall;
	}

	public void setQqComicInstall(int qqComicInstall) {
		this.qqComicInstall = qqComicInstall;
	}

	public int getShuqiInstall() {
		return shuqiInstall;
	}

	public void setShuqiInstall(int shuqiInstall) {
		this.shuqiInstall = shuqiInstall;
	}

	public List<PreList> getPreList() {
		return preList;
	}

	public void setPreList(List<PreList> preList) {
		this.preList = preList;
	}

	public List<AccPreList> getAccPreList() {
		return accPreList;
	}

	public void setAccPreList(List<AccPreList> accPreList) {
		this.accPreList = accPreList;
	}

	public List<RedList> getRedList() {
		return redList;
	}

	public void setRedList(List<RedList> redList) {
		this.redList = redList;
	}

	public boolean isShowRemind() {
		return showRemind;
	}

	public void setShowRemind(boolean showRemind) {
		this.showRemind = showRemind;
	}

	@Override
	public String toString() {
		return "TaskList [app=" + app + ", update=" + update + ", updateUrl=" + updateUrl + ", times=" + times
				+ ", showCoupon=" + showCoupon + ", subscribeUrl=" + subscribeUrl + ", qqComicInstall=" + qqComicInstall
				+ ", shuqiInstall=" + shuqiInstall + ", preList=" + preList + ", accPreList=" + accPreList
				+ ", redList=" + redList + ", showRemind=" + showRemind + "]";
	}
	
	
}
