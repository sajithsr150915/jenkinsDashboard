
package com.jenkins.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jenkins.model.*;
import com.jenkins.service.JenkinsCountDetail;
import com.jenkins.service.ManageJenkinsService;
import com.jenkins.service.TestJobCountDetail;
import com.jenkins.service.TestJobDetail;


@RestController
@RequestMapping("/manageJenkins")
public class ManageJenkinsController {
	
	@Autowired(required=true)
	ManageJenkinsService manageJenkinsService;
	
	
	//1.find the build
	
	@GetMapping("/jenkinsBuildDetails")
	public JenkinsCountDetail getCountDetailsofJenkins() {

		return manageJenkinsService.getCountDetailsofJenkins(null);

	}
	
	
	@GetMapping("/jobBuildDetails")
	public Map<String,JenkinsCountDetail> getCountDetailsofJobs() {

		return manageJenkinsService.getCountDetailsofJobs();

	}
	
	
	@GetMapping("/uatDeployment")
	public JenkinsCountDetail uatDeployment() {

		return manageJenkinsService.uatDeployments();

	}
	
	
	@GetMapping("/prodDeployment")
	public JenkinsCountDetail prodDeployment() {

		return manageJenkinsService.prodDeployments();

	}
	
	
	@GetMapping("/lastSuccesfulUAT")
	public  List<Deployment> lastSuccesfulUAT() {
  
		return manageJenkinsService.lastSuccesfulUAT(); 

	}
	
	
	@GetMapping("/lastSuccesfulPROD")
	public  List<Deployment> lastSuccesfulPROD() {
  
		return manageJenkinsService.lastSuccesfulPROD(); 

	}
	
	@GetMapping("/acceptanceTest")
	public  TestJobCountDetail acceptanceTest() {
  
		return manageJenkinsService.acceptanceTest(); 

	}
	
	@GetMapping("/acceptanceTestGroup")
	public  List<TestJobDetail> acceptanceTestGroup() {
  
		return manageJenkinsService.acceptanceTestGroup(); 

	}
	
	
}