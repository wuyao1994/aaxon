package com.aaxis.microservice.training.demo1.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Product")
public class ProductJpa {

    @Id
    private String id;
    private String name;
    private Integer priority;
    private Date createdDate;

    @Transient
    private double price;
    @Transient
    private int stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryJpa category;

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

    public CategoryJpa getCategory() {
        return category;
    }

    public void setCategory(CategoryJpa pCategory) {
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date pCreatedDate) {
        createdDate = pCreatedDate;
    }
}
