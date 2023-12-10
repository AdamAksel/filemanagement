package com.example.filemanagement.repository;

import com.example.filemanagement.model.Folder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FolderRepository extends MongoRepository<Folder, String> {
}
