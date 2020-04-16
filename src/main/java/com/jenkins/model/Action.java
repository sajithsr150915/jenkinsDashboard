package com.jenkins.model;


	public class Action {
		
		
	 private int failCount;
	 private int skipCount;
	 private int totalCount;
	 private String urlName;
	 
	
	public int getFailCount() {
		return failCount;
	}
	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}
	public int getSkipCount() {
		return skipCount;
	}
	public void setSkipCount(int skipCount) {
		this.skipCount = skipCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getUrlName() {
		return urlName;
	}
	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	 
	 

	}

