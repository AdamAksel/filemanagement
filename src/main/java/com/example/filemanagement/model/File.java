package com.example.filemanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class File {
    @Id
    private String id;
    private String name;
    private String folderId;
    private String path;
    private long size;

    public File() {}

    public File(String name, String folderId, long size) {
        this.name = name;
        this.folderId = folderId;
        this.size = size;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getId() {
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }


    public void setSize(long size) {
        this.size = size;
    }
}


