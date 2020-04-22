
package com.jenkins.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jenkins.model.Deployment;
import com.jenkins.model.TestJobCountDetail;
import com.jenkins.model.TestJobDetail;
import com.jenkins.model.JenkinsCountDetail;
import com.jenkins.model.JenkinsJobCountDetail;
import com.jenkins.service.ManageJenkinsService;


@RestController
@RequestMapping("/jenkinsJob")
public class JenkinsJobController {
	
	@Autowired(required=true)
	ManageJenkinsService manageJenkinsService;
	
	/**
	 * @return JenkinsCountDetail
	 * Method to get the total build count with in default time frame.
	 * @ 
	 */
	@GetMapping(value= {"/totalBuildCountJenkins"})
	public JenkinsCountDetail getTotalBuildCountJenkins()  { 
		return manageJenkinsService.getTotalBuildCountJenkins(null); 

	}
	
	
	/**
	 * @return JenkinsCountDetail
	 * Method to get the total build count of UAT with in default time frame.
	 * @ 
	 */
	@GetMapping(value= {"/totalBuildCountUAT"})
	public JenkinsCountDetail getTotalBuildCountUAT()  {

		return manageJenkinsService.getTotalBuildCountUAT(null);  

	}
	
	/**
	 * @return JenkinsCountDetail
	 * Method to get the total build count of PROD with in default time frame.
	 * @ 
	 */
	@GetMapping(value= {"/totalBuildCountPROD"})
	public JenkinsCountDetail getTotalBuildCountPROD()  {

		return manageJenkinsService.getTotalBuildCountPROD(null); 

	} 
	
	
	/**
	 * @return TestJobCountDetail
	 * Method to get the test case count of Acceptance pipeline
	 * @ 
	 */
	@GetMapping(value= {"/totalCountAcceptance"})
	public TestJobCountDetail getTotalCountAcceptance()  {

		return manageJenkinsService.getTotalCountAcceptance(null);

	}
	
	

	/**
	 * @return Map<String, JenkinsCountDetail>
	 * Method to get the count of builds grouped by job
	 * @ 
	 */
	@GetMapping(value= {"/countGroupByJenkinsJob"})
	public List<JenkinsJobCountDetail> getCountGroupByJenkinsJob()  {

		return manageJenkinsService.getCountGroupByJenkinsJob(null); 

	}
	
	/**
	 * @return Map<String, JenkinsCountDetail>
	 * Method to get the count of UAT builds grouped by job
	 * @ 
	 */
	@GetMapping(value= {"/countGroupByUAT"})
	public List<JenkinsJobCountDetail> getCountGroupByUATDeploymnt()  {

		return manageJenkinsService.getCountGroupByUATDeploymnt(null); 

	}
	
	/**
	 * @return Map<String, JenkinsCountDetail>
	 * Method to get the count of PROD builds grouped by job
	 * @ 
	 */
	@GetMapping(value= {"/countGroupByPROD"})
	public List<JenkinsJobCountDetail> getCountGroupByPRODDeploymnt()  {

		return manageJenkinsService.getCountGroupByPRODDeploymnt(null); 

	}	
	
	/**
	 * @return Map<String, TestJobCountDetail>
	 * Method to get the test case count grouped by job
	 * @ 
	 */
	@GetMapping(value= {"/countGroupByAcceptancTest"})
	public List<TestJobDetail> getCountGroupByAcceptancTest()  {

		return manageJenkinsService.getCountGroupByAcceptancTest(null);    

	}
	
	/**
	 * @return JenkinsCountDetail
	 * Method to get the total build count with in day limit
	 * @ 
	 */
	@GetMapping(value= {"/totalBuildCountJenkins/{dayLimit}"})
	public JenkinsCountDetail getTotalBuildCountJenkins(@PathVariable(name = "dayLimit") Integer dayLimit)  {

		return manageJenkinsService.getTotalBuildCountJenkins(dayLimit); 

	}
	
	/**
	 * @param dayLimit
	 * @return JenkinsCountDetail
	 * Method to get the total build count with in day limit
	 * @ 
	 */
	@GetMapping(value= {"/totalBuildCountUAT/{dayLimit}"})
	public JenkinsCountDetail getTotalBuildCountUAT(@PathVariable(name = "dayLimit") Integer dayLimit)  {

		return manageJenkinsService.getTotalBuildCountUAT(dayLimit); 

	}
	
	
	/**
	 * @param dayLimit
	 * @return JenkinsCountDetail
	 * Method to get the total build count of PROD with in day limit
	 * @ 
	 */
	@GetMapping(value= {"/totalBuildCountPROD/{dayLimit}"})
	public JenkinsCountDetail getTotalBuildCountPROD(@PathVariable(name = "dayLimit") Integer dayLimit)  {

		return manageJenkinsService.getTotalBuildCountPROD(dayLimit); 

	}
	
	/**
	 * @param dayLimit
	 * @return TestJobCountDetail
	 * Method to get the test case count of Acceptance pipeline with in day limit
	 * @ 
	 */
	@GetMapping(value= {"/totalCountAcceptance/{dayLimit}"})
	public TestJobCountDetail getTotalCountAcceptance(@PathVariable(name = "dayLimit") Integer dayLimit)  {

		return manageJenkinsService.getTotalCountAcceptance(dayLimit);

	}
	
	
	/**
	 * @param dayLimit
	 * @return Map<String, JenkinsCountDetail>
	 * Method to get the count of builds grouped by job with in day limit
	 * @ 
	 */
	@GetMapping(value= {"/countGroupByJenkinsJob/{dayLimit}"})
	public List<JenkinsJobCountDetail> getCountGroupByJenkinsJob(@PathVariable(name = "dayLimit") Integer dayLimit)  {

		return manageJenkinsService.getCountGroupByJenkinsJob(dayLimit); 

	}
	
	/**
	 * @param dayLimit
	 * @return Map<String, JenkinsCountDetail>
	 * Method to get the count of UAT builds grouped by job with in day limit
	 * @ 
	 */
	@GetMapping(value= {"/countGroupByUAT/{dayLimit}"})
	public List<JenkinsJobCountDetail> getCountGroupByUATDeploymnt(@PathVariable(name = "dayLimit") Integer dayLimit)  {

		return manageJenkinsService.getCountGroupByUATDeploymnt(dayLimit); 

	}
	
	/**
	 * @param dayLimit
	 * @return Map<String, JenkinsCountDetail>
	 * Method to get the count of PROD builds grouped by job with in day limit
	 * @ 
	 */
	@GetMapping(value= {"/countGroupByPROD/{dayLimit}"})
	public List<JenkinsJobCountDetail> getCountGroupByPRODDeploymnt(@PathVariable(name = "dayLimit") Integer dayLimit)  {

		return manageJenkinsService.getCountGroupByPRODDeploymnt(dayLimit); 

	}	
	
	/**
	 * @param dayLimit
	 * @return Map<String, TestJobCountDetail>
	 * Method to get the test case count grouped by job with in day limit
	 * @ 
	 */
	@GetMapping(value= {"/countGroupByAcceptancTest/{dayLimit}"})
	public List<TestJobDetail> getCountGroupByAcceptancTest(@PathVariable(name = "dayLimit") Integer dayLimit)  {

		return manageJenkinsService.getCountGroupByAcceptancTest(dayLimit);    

	}
	
	
	/**
	 * @return List<Deployment>
	 * Method to get last UAT deployments
	 * @ 
	 */
	@GetMapping(value= {"/lastUATDeployments"})
	public List<Deployment> lastUATDeployments()  {
		return manageJenkinsService.lastUATDeployments();    

	}
		
	/**
	 * @return List<Deployment>
	 * Method to get last PROD deployments
	 * @ 
	 */
	@GetMapping(value= {"/lastPRODDeployments"})
	public List<Deployment> lastPRODDeployments()  {
		return manageJenkinsService.lastProdDeployments();    

	}
		
		
		
		
		
		
		
	
	
	
}