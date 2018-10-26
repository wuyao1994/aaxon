package com.aaxis.microservice.training.demo1.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Document(indexName = "mysql", type = "inventory", refreshInterval = "0s")
public class Inventory implements Serializable {
	@Id
	private String	id;
	private int		stock;



	public String getId() {
		return id;
	}



	public void setId(String pId) {
		id = pId;
	}



	public int getStock() {
		return stock;
	}



	public void setStock(int pStock) {
		stock = pStock;
	}
}
