package com.aaxis.microservice.training.demo1.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Document(indexName = "aaxon", type = "microservice", refreshInterval = "0s")
public class ItemPrice implements Serializable {
	@Id
	private String	id;
	private double	price;



	public String getId() {
		return id;
	}



	public void setId(String pId) {
		id = pId;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double pPrice) {
		price = pPrice;
	}
}
