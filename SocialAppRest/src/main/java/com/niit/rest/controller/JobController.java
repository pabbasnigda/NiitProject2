package com.niit.rest.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.rest.dao.JobDAO;
import com.niit.rest.model.Job;


@RestController
public class JobController 
{
	@Autowired 
	JobDAO jobDAO;
	
	@PostMapping(value="/insertJob")
	public ResponseEntity<String> insertJob(@RequestBody Job job)
	{
		if(jobDAO.addJob(job))
		{
			return new ResponseEntity<String>("Job Added",HttpStatus.OK);	
		}
		else
		{
			return new ResponseEntity<String>("Error in Response Entity",HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@PostMapping(value="/updateJob")
	public ResponseEntity<String> updateJob(@RequestBody Job job)
	{
		Job tempJob=jobDAO.getJob(job.getJobId());
		
		tempJob.setJobDesc(job.getJobDesc());
		tempJob.setQualification(job.getQualification());
		
		if(jobDAO.updateJob(tempJob))
		{
			return new ResponseEntity<String>("Job updated",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Error in Response Entity",HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	@GetMapping(value="/getAllJobs")
	public ResponseEntity<ArrayList<Job>> getAllJobs()
	{
		ArrayList listJobs=(ArrayList)jobDAO.getAllJobs();
		return new ResponseEntity<ArrayList<Job>>(listJobs,HttpStatus.SERVICE_UNAVAILABLE);
	}
}