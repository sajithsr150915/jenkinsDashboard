package com.jenkins.service;

public class TestJobDetail {
	
	private String  name;
	private  int totalCount;
	private int successCount;
	private int failureCount;
	private int skippedCount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}
	public int getFailureCount() {
		return failureCount;
	}
	public void setFailureCount(int failureCount) {
		this.failureCount = failureCount;
	}
	public int getSkippedCount() {
		return skippedCount;
	}
	public void setSkippedCount(int skippedCount) {
		this.skippedCount = skippedCount;
	}
	
	
	


}
