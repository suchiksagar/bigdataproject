package com.bigdata.project.filepoll.ws.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/filepoll")
public class FilePollController {

	//private static final String FOLDER_NAME = "/home/venom/bigdata/weatherdata/dump";
	private static final String FOLDER_NAME = "/tmp/bigdata/weatherdata/dump";
	

	@GetMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<byte[]> getDownloadData() throws IOException {
		Path dir = Paths.get(FOLDER_NAME);
		Optional<Path> lastFilePath = Files.list(dir).filter(f -> !Files.isDirectory(f))
				.max(Comparator.comparingLong(f -> f.toFile().lastModified()));
		if (lastFilePath.isPresent()) {
			File file = lastFilePath.get().toFile();
			System.out.println("File Name: " + file.getName());
			byte[] output = Files.readAllBytes(lastFilePath.get());
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("charset", "utf-8");
			responseHeaders.setContentType(MediaType.valueOf("text/html"));
			responseHeaders.setContentLength(output.length);
			responseHeaders.set("Content-disposition", "attachment; filename=" + file.getName());

			return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);
		}
		return null;
	}
}
