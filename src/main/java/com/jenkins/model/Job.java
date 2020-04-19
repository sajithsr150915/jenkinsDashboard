package com.jenkins.model;

import java.util.ArrayList;
import java.util.List;

public class Job {

	private String name;
	private String url;
	List<Build> allBuilds = new ArrayList<>();
	private Build lastSuccessfulBuild;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



	public List<Build> getAllBuilds() {
		return allBuilds;
	}

	public void setAllBuilds(List<Build> allBuilds) {
		this.allBuilds = allBuilds;
	}

	public Build getLastSuccessfulBuild() {
		return lastSuccessfulBuild;
	}

	public void setLastSuccessfulBuild(Build lastSuccessfulBuild) {
		this.lastSuccessfulBuild = lastSuccessfulBuild;
	}

}
