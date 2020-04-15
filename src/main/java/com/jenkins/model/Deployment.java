package com.jenkins.model;



public class Deployment {
	
	private String jobName;
	private long lastSuccessfulRun;
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public long getLastSuccessfulRun() {
		return lastSuccessfulRun;
	}
	public void setLastSuccessfulRun(long lastSuccessfulRun) {
		this.lastSuccessfulRun = lastSuccessfulRun;
	}
	
	
	

}
