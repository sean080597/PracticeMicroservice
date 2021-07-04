package com.example.batchjob.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.batchjob.service.ExampleService;

@RestController
@RequestMapping("/batch")
public class BatchController {

	@Autowired
	private ExampleService exampleService;
	
	@GetMapping("/invokejob")
	public String handle() throws Exception {
		exampleService.launchJob();
		return "Batch job has been invoked";
	}
}
