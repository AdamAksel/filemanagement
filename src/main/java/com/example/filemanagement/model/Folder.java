package com.example.filemanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Folder {
    @Id
    private String id;
    private String name;
    private String userId;


    public Folder(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }


    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

}

