package com.ninja.file;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ninja.file.service.FolderInitService;

@SpringBootApplication
public class FileStorageApplication implements CommandLineRunner{
	
	@Autowired
	private FolderInitService folderService;

	public static void main(String[] args) {
		final Path path = Paths.get("uploads");
		System.out.println(path);
		
		SpringApplication.run(FileStorageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		folderService.init();
	}

}
