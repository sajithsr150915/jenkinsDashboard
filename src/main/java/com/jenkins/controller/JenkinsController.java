
package com.jenkins.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jenkins.service.JenkinsService;


@RestController
public class JenkinsController {
	
	@Autowired(required=true)
	JenkinsService jenkinsService;
	
	

	
	/**
	 * @return String 
	 * The method invokes the jenkins API
	 */
	@GetMapping("/jenkinsDetails")
    public String  jenkinsDetails() 
    {
		
		
	return jenkinsService.getJenkinsDetails();  
		
		

/*http://localhost:8080/api/json?tree=jobs[name,url,builds[number,result,duration,url]] 

http://localhost:8080/job/build%20project/31/api/json

http://localhost:8080/api/json?tree=jobs[name,url,builds[number,result,duration,url,actions[buildsByBranchName]]]

http://localhost:8080/api/json?tree=jobs[name,url,builds[number,result,duration,url,actions[buildsByBranchName.refs/remotes/origin/master]]]


http://localhost:8080/api/json?tree=jobs[name,url,builds[number,result,duration,url,actions[buildsByBranchName[*[*[*]]]]]]




*/
    }
	/**
	 * @return String 
	 * The method invokes the jenkins API and produce results with test report
	 */
	@GetMapping("/jenkinswithTestReport")
    public String  jenkinswithTestReport() 
    {
		
		
	return jenkinsService.jenkinswithTestReport();  
		
    }
	
	/**sample selenuim projects
	 * https://github.com/reportportal/example-cucumber-junit-selenium-logback-maven.git
	 * https://github.com/kolorobot/spring-boot-thymeleaf.git
	 * https://github.com/paulvi/selenium-for-spring-boot
	 * 
	 * In post build action give test report Xmls- target/surefire-reports/*.xml
	 * use Test Result Analyzer plugin
	 * 
	 */
}
