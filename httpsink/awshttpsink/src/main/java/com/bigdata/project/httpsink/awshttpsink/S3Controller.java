package com.bigdata.project.httpsink.awshttpsink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/publish")
public class S3Controller {
	
	@Autowired
	private AmazonS3ContentPublisher contentPublisher;
	
	@PostMapping
	public String publish(@RequestBody String body) {
		try {
			contentPublisher.publishMessage(body);
			return "Success";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Failed to upload";
		}
	}

}
