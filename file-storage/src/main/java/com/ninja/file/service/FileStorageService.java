package com.ninja.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ninja.file.entity.FileData;
import com.ninja.file.repo.FileStorageRepo;

@Service
public class FileStorageService {
	
	@Autowired
	private FileStorageRepo repo;	
	
	final Path root = Paths.get("uploads");
	
	public String uploadImageToFileSystem(MultipartFile file) throws IllegalStateException, IOException {
		Path filePath = root.resolve(file.getOriginalFilename()); // returns uploads\product1.jpg
		
		FileData fileData = repo.save(FileData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.filePath(filePath.toString()).build());
		
		Files.copy(file.getInputStream(), filePath);
		
		if (null != fileData) {
			return "File uploaded successfully at : %s".formatted(filePath);
		}
		
		return null;
	}
	
	
	public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
		Optional<FileData> fileData = repo.findByName(fileName);
		String filePath = fileData.get().getFilePath();
		byte[] images = Files.readAllBytes(new File(filePath).toPath());
		
		return images;
	}

}
