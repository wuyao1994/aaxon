package com.aaxis.microservice.training.demo1.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Document(indexName = "mysql", type = "category", refreshInterval = "0s")
public class Category {

    @Id
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String pId) {
        id = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }
}
