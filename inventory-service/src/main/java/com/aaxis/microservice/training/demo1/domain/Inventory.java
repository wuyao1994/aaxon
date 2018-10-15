package com.aaxis.microservice.training.demo1.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
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
