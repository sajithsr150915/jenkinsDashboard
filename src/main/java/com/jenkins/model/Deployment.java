package com.jenkins.model;

import java.time.LocalDate;

public class Deployment {

	private String jobName;
	private long lastSuccessfulRun;
	private LocalDate successfulRunDate;

	public Deployment() {
	}

	public Deployment(String jobName, long lastSuccessfulRun, LocalDate successfulRunDate) {
		this.jobName = jobName;
		this.lastSuccessfulRun = lastSuccessfulRun;
		this.successfulRunDate = successfulRunDate;
	}

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

	public LocalDate getSuccessfulRunDate() {
		return successfulRunDate;
	}

	public void setSuccessfulRunDate(LocalDate successfulRunDate) {
		this.successfulRunDate = successfulRunDate;
	}

}
