package com.ninja.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninja.file.repo.FileStorageRepo;

@Service
public class FileStorageService {
	
	@Autowired
	private FileStorageRepo repo;

}
