package com.example.demo.service.file;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.FileEntity;
import com.example.demo.model.User;
import com.example.demo.repositry.FileRepositry;

@Service
public class UploadFileService {
	private final FileRepositry fileRepositry;

	public UploadFileService(FileRepositry fileRepositry) {
		this.fileRepositry = fileRepositry;
	}

	public void uploadFile(MultipartFile file, User user) {
		String fileName = file.getOriginalFilename();
		String nameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
		String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
		FileEntity fileObj = new FileEntity();
		fileObj.setFileName(nameWithoutExtension);
		fileObj.setFileSize(file.getSize());
		fileObj.setFileType(extension);
		fileObj.setUser(user);
		fileObj.setUuid(UUID.randomUUID().toString());
		fileRepositry.save(fileObj);
	}
}
