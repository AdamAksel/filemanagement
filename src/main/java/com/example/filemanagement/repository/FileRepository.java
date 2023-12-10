package com.example.filemanagement.repository;

import com.example.filemanagement.model.File;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<File, String> {

}

