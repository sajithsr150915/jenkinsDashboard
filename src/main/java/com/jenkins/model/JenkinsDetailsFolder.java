package com.jenkins.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JenkinsDetailsFolder {
	
	 private  JobFolder job;

	public JobFolder getJob() {
		return job;
	}

	public void setJob(JobFolder job) {
		this.job = job;
	}

	 
	
	}












