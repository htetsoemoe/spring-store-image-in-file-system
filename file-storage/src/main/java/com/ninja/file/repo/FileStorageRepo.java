package com.ninja.file.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ninja.file.entity.FileData;

public interface FileStorageRepo extends JpaRepository<FileData, Integer>{

	Optional<FileData> findByName(String fileName);
}
