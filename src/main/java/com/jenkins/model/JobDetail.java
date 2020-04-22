package com.jenkins.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobDetail {

	List<BuildDetail> allBuild = new ArrayList<>();
	List<BuildDetail> lastSuccessfulBuild  = new ArrayList<>();
	

	public List<BuildDetail> getAllBuild() {
		return allBuild;
	}

	public void setAllBuild(List<BuildDetail> allBuild) {
		this.allBuild = allBuild;
	}

	public List<BuildDetail> getLastSuccessfulBuild() {
		return lastSuccessfulBuild;
	}

	public void setLastSuccessfulBuild(List<BuildDetail> lastSuccessfulBuild) {
		this.lastSuccessfulBuild = lastSuccessfulBuild;
	}

	
	
	
	

	
	
	
	
}
