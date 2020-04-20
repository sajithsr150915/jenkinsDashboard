
package com.jenkins.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jenkins.model.Deployment;
import com.jenkins.service.JenkinsCountDetail;
import com.jenkins.service.ManageJenkinsService;
import com.jenkins.service.TestJobCountDetail;


@RestController
@RequestMapping("/jenkinsJob")
public class JenkinsJobController {
	
	@Autowired(required=true)
	ManageJenkinsService manageJenkinsService;
	
	@GetMapping(value= {"/totalBuildCountJenkins"})
	public JenkinsCountDetail getTotalBuildCountJenkins() {
 
		return manageJenkinsService.getTotalBuildCountJenkins(null); 

	}
	
	@GetMapping(value= {"/totalBuildCountUAT"})
	public JenkinsCountDetail getTotalBuildCountUAT() {

		return manageJenkinsService.getTotalBuildCountUAT(null);  

	}
	
	
	@GetMapping(value= {"/totalBuildCountPROD"})
	public JenkinsCountDetail getTotalBuildCountPROD() {

		return manageJenkinsService.getTotalBuildCountPROD(null); 

	}
	
	@GetMapping(value= {"/totalCountAcceptance"})
	public TestJobCountDetail getTotalCountAcceptance() {

		return manageJenkinsService.getTotalCountAcceptance(null);

	}
	
	
	
	@GetMapping(value= {"/countGroupByJenkinsJob"})
	public Map<String, JenkinsCountDetail> getCountGroupByJenkinsJob() {

		return manageJenkinsService.getCountGroupByJenkinsJob(null,null); 

	}
	
	@GetMapping(value= {"/countGroupByUAT"})
	public Map<String, JenkinsCountDetail> getCountGroupByUATDeploymnt() {

		return manageJenkinsService.getCountGroupByUATDeploymnt(null); 

	}
	
	@GetMapping(value= {"/countGroupByPROD"})
	public Map<String, JenkinsCountDetail> getCountGroupByPRODDeploymnt() {

		return manageJenkinsService.getCountGroupByPRODDeploymnt(null); 

	}	
	
	@GetMapping(value= {"/countGroupByAcceptancTest"})
	public Map<String, TestJobCountDetail> getCountGroupByAcceptancTest() {

		return manageJenkinsService.getCountGroupByAcceptancTest(null);    

	}
	
	
	@GetMapping(value= {"/totalBuildCountJenkins/{dayLimit}"})
	public JenkinsCountDetail getTotalBuildCountJenkins(@PathVariable(name = "dayLimit") Integer dayLimit) {

		return manageJenkinsService.getTotalBuildCountJenkins(dayLimit); 

	}
	
	@GetMapping(value= {"/totalBuildCountUAT/{dayLimit}"})
	public JenkinsCountDetail getTotalBuildCountUAT(@PathVariable(name = "dayLimit") Integer dayLimit) {

		return manageJenkinsService.getTotalBuildCountUAT(dayLimit); 

	}
	
	
	@GetMapping(value= {"/totalBuildCountPROD/{dayLimit}"})
	public JenkinsCountDetail getTotalBuildCountPROD(@PathVariable(name = "dayLimit") Integer dayLimit) {

		return manageJenkinsService.getTotalBuildCountPROD(dayLimit); 

	}
	
	@GetMapping(value= {"/totalCountAcceptance/{dayLimit}"})
	public TestJobCountDetail getTotalCountAcceptance(@PathVariable(name = "dayLimit") Integer dayLimit) {

		return manageJenkinsService.getTotalCountAcceptance(dayLimit);

	}
	
	
	
	@GetMapping(value= {"/countGroupByJenkinsJob/{dayLimit}"})
	public Map<String, JenkinsCountDetail> getCountGroupByJenkinsJob(@PathVariable(name = "dayLimit") Integer dayLimit) {

		return manageJenkinsService.getCountGroupByJenkinsJob(null,dayLimit); 

	}
	
	@GetMapping(value= {"/countGroupByUAT/{dayLimit}"})
	public Map<String, JenkinsCountDetail> getCountGroupByUATDeploymnt(@PathVariable(name = "dayLimit") Integer dayLimit) {

		return manageJenkinsService.getCountGroupByUATDeploymnt(dayLimit); 

	}
	
	@GetMapping(value= {"/countGroupByPROD/{dayLimit}"})
	public Map<String, JenkinsCountDetail> getCountGroupByPRODDeploymnt(@PathVariable(name = "dayLimit") Integer dayLimit) {

		return manageJenkinsService.getCountGroupByPRODDeploymnt(dayLimit); 

	}	
	
	@GetMapping(value= {"/countGroupByAcceptancTest/{dayLimit}"})
	public Map<String, TestJobCountDetail> getCountGroupByAcceptancTest(@PathVariable(name = "dayLimit") Integer dayLimit) {

		return manageJenkinsService.getCountGroupByAcceptancTest(dayLimit);    

	}
	
	@GetMapping(value= {"/lastUATDeployments"})
	public List<Deployment> lastUATDeployments() {
		return manageJenkinsService.lastUATDeployments();    

	}
		

	@GetMapping(value= {"/lastPRODDeployments"})
	public List<Deployment> lastPRODDeployments() {
		return manageJenkinsService.lastProdDeployments();    

	}
		
		
		
		
		
		
		
	
	
	
}