package com.nagarro.training.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.training.model.File;
import com.nagarro.training.service.FileService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {
	@Autowired
	private FileService fileService;

//  Get a particular file
	@RequestMapping(value = "/files/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getFile(@PathVariable String id) {
		try {
			File file = fileService.getFile(id);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
					.body(file.getData());
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Files does not exist.");
		}
	}
}
