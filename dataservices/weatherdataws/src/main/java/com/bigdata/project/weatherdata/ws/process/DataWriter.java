package com.bigdata.project.weatherdata.ws.process;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.stereotype.Component;

@Component
public class DataWriter {
	
	private static final String FILE_NAME = "/tmp/bigdata/weatherdata/dump/data-dump-";
	//private static final String FILE_NAME = "/home/ec2-user/bigdata/weatherdata/dump/data-dump-";
	
	public void publishResponse(String response) throws IOException {
		long currentTime = System.currentTimeMillis();
		Path filePath = Paths.get(FILE_NAME+currentTime+".json");
		Path parentDir = filePath.getParent();
		if (!Files.exists(parentDir)) {
		    Files.createDirectories(parentDir);
		}
		Files.write(filePath, response.getBytes(), StandardOpenOption.CREATE);
		System.out.println("logged to file");
	}

}
