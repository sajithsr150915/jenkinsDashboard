package com.jenkins.service;

import java.io.IOException;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jenkins.model.BuildInfo;
import com.jenkins.model.Item;
import com.jenkins.model.JenkinsDetails;
import com.jenkins.model.JobDetails;
import com.jenkins.model.JobSpecificDetails;
import com.jenkins.model.LastBuild;
import com.jenkins.model.OverallLoad;
import com.jenkins.model.PipelineNode;
import com.jenkins.model.WorkFlow;
import com.jenkins.model.QueueList;
import com.jenkins.model.PluginManager;


@Service
@Component
public class ManageJenkinsService {

	
	
	
	
	
	

}
