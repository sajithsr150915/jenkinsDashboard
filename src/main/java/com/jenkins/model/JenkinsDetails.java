package com.jenkins.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JenkinsDetails {
	
	 private  JenkinsDetailsFolder job;

	public JenkinsDetailsFolder getJob() {
		return job;
	}

	public void setJob(JenkinsDetailsFolder job) {
		this.job = job;
	}

	

	 
	
	}












