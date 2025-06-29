package com.example.demo.service.encDec;

import org.springframework.web.multipart.MultipartFile;

public interface EncryptionService {
	String encryptAndSaveFile(MultipartFile file, String destinationPath) throws Exception;

	byte[] decryptFile(String filePath) throws Exception;
}
