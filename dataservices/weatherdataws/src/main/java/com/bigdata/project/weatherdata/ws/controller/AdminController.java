package com.bigdata.project.weatherdata.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bigdata.project.weatherdata.ws.service.DataCollectorService;

@RestController
@RequestMapping("/v1/admin")
public class AdminController {

	private static final String SUCCESS = "success";

	@Autowired
	private DataCollectorService service;

	@GetMapping("/start")
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	public String start() {
		try {
			return SUCCESS + " ==> " + service.beginCollection();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Failure ==> " + ex.getMessage();
		}
	}

	@GetMapping("/stop")
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	public String stop() {
		String response = service.stopCollection();
		return SUCCESS + " ==> " + response;
	}

}
