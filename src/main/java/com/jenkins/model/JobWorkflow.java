package com.jenkins.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobWorkflow {
	
	 private  List<JobDetail> job=new ArrayList<>();

	public List<JobDetail> getJob() {
		return job;
	}

	public void setJob(List<JobDetail> job) {
		this.job = job;
	}
	 
	 



	
	 
	
	 
	
	}












