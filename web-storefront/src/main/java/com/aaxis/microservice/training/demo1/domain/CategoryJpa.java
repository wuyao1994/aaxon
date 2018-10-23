package com.aaxis.microservice.training.demo1.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "Category")
public class CategoryJpa {

    @Id
    private String id;
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<ProductJpa> mProducts;



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



    public Set<ProductJpa> getProducts() {
        return mProducts;
    }



    public void setProducts(Set<ProductJpa> pProducts) {
        mProducts = pProducts;
    }
}
