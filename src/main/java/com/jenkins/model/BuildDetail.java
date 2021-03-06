package com.jenkins.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuildDetail {
		
	 private String id;
	 private int number;
	 private String result;
	 private long timestamp;
	 private long duration;
	 private List<Action> action=new ArrayList<>();

	 private String url;
	  String jobName;
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	public List<Action> getAction() {
		return action;
	}
	public void setAction(List<Action> action) {
		this.action = action;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getJobName() {
	
		
		String[] urlArray=url.split("/job/");
		StringBuilder job = new StringBuilder("");

		for(int count=1;count<urlArray.length;count++) {
	
			if(urlArray[count].contains("/")) {
				job=job.append(urlArray[count].substring(0,urlArray[count].indexOf('/')));
			}else {
				job=job.append(urlArray[count]).append("/");
			}
				
		}
		return job.toString();
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	
	

	 
	
	


	}

