package com.bigdata.project.weatherdata.ws.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.bigdata.project.weatherdata.ws.client.OpenWeatherMapApiClient;
import com.bigdata.project.weatherdata.ws.process.DataProcessor;
import com.bigdata.project.weatherdata.ws.process.DataWriter;
import com.bigdata.project.weatherdata.ws.process.PropertyHandler;

@Service
public class DataCollectorService {
	
	private static final String THREAD_NAME = "data-collector";

	@Autowired
	private OpenWeatherMapApiClient client;
	@Autowired
	private PropertyHandler propertyHandler;
	@Autowired
	private DataWriter dataWriter;
	@Autowired
	private ResourceLoader resourceLoader;
	
	ScheduledExecutorService executorService;
	
	public String beginCollection() throws IOException, URISyntaxException {
		if(executorService != null) {
			return "a job is already running";
		}
		executorService = Executors.newSingleThreadScheduledExecutor();
		Future<?> resultFuture = executorService.scheduleAtFixedRate(getProcessor(), 1, 12, TimeUnit.SECONDS);
		return "kicked off the job";
	}
	
	protected DataProcessor getProcessor() throws IOException, URISyntaxException {
		return new DataProcessor(client, propertyHandler, dataWriter, resourceLoader);
	}
	
	public String stopCollection() {
		if(executorService == null) {
			return "Failed to stop since no job is running";
		}
		executorService.shutdown();
		try {
		    if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
		        executorService.shutdownNow();
		    } 
		} catch (InterruptedException e) {
		    executorService.shutdownNow();
		}
		executorService = null;
		return "stopped the job";
	}
}
