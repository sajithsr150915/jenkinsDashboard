
package com.jenkins.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jenkins.service.JenkinsService;


@RestController
public class JenkinsController {
	
	@Autowired(required=true)
	JenkinsService jenkinsService;
	
	

	@GetMapping("/jenkinsDetails")
    public String  jenkinsDetails() 
    {
		
		
	String value= jenkinsService.jenkinsGetAPI();
		
		return value;

/*http://localhost:8080/api/json?tree=jobs[name,url,builds[number,result,duration,url]] 

http://localhost:8080/job/build%20project/31/api/json

http://localhost:8080/api/json?tree=jobs[name,url,builds[number,result,duration,url,actions[buildsByBranchName]]]

http://localhost:8080/api/json?tree=jobs[name,url,builds[number,result,duration,url,actions[buildsByBranchName.refs/remotes/origin/master]]]


http://localhost:8080/api/json?tree=jobs[name,url,builds[number,result,duration,url,actions[buildsByBranchName[*[*[*]]]]]]




*/
    }
	
	
	
}
