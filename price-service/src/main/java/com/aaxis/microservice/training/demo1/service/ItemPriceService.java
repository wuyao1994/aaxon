package com.aaxis.microservice.training.demo1.service;

import com.aaxis.microservice.training.demo1.domain.ItemPrice;

public interface ItemPriceService {


	public ItemPrice findItemPriceById(String pProductId);

}
