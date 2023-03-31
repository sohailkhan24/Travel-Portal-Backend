package com.nagarro.training.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nagarro.training.model.File;
import com.nagarro.training.repository.FileRepository;
import com.nagarro.training.repository.TicketRepository;
import com.nagarro.training.utility.ResponseFile;

@Service
public class FileService {
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private TicketRepository ticketRepository;

	public boolean addFile(MultipartFile multipartFile, String ticketId) {
		try {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			File file = new File(fileName, multipartFile.getBytes(), ticketRepository.findById(ticketId).get());
			fileRepository.save(file);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public File getFile(String id) {
		if (fileRepository.existsById(id)) {
			File file = fileRepository.findById(id).get();
			return file;
		}
		return null;
	}

	public List<ResponseFile> getResponseFiles(List<File> files) {
		List<ResponseFile> responseFiles = new ArrayList<>();
		for (File file : files) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(file.getId()).toUriString();
			responseFiles.add(new ResponseFile(file.getName(), fileDownloadUri));
		}
		return responseFiles;
	}
}
