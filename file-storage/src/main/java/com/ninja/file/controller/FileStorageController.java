package com.ninja.file.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ninja.file.service.FileStorageService;

@RestController
@RequestMapping("/images")
public class FileStorageController {
	
	@Autowired
	private FileStorageService storageService;
	
	@PostMapping("/file-system")
	public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image") MultipartFile file) throws IllegalStateException, IOException {
		String uploadImage = storageService.uploadImageToFileSystem(file);
		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
	}
	
	@GetMapping("/file-system/{fileName}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
		byte[] imageData = storageService.downloadImageFromFileSystem(fileName);
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/jpg"))
				.body(imageData);
	}

}
