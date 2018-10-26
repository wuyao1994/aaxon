package com.aaxis.microservice.training.demo1.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.Date;

@Document(indexName = "mysql", type = "product", refreshInterval = "0s")
public class Product {

    @Id()
    private String id;
    private String name;
    private Integer priority;
    private Date created_date;

    @Transient
    private double price;
    @Transient
    private int stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double pPrice) {
        price = pPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category pCategory) {
        category = pCategory;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int pStock) {
        stock = pStock;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer pPriority) {
        priority = pPriority;
    }



    public Date getCreated_date() {
        return created_date;
    }



    public void setCreated_date(Date pCreated_date) {
        created_date = pCreated_date;
    }
}
