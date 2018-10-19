package com.aaxis.microservice.training.demo1.service.impl;

import com.aaxis.microservice.training.demo1.dao.ItemPriceDAO;
import com.aaxis.microservice.training.demo1.domain.ItemPrice;
import com.aaxis.microservice.training.demo1.service.ItemPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemPriceServiceImpl implements ItemPriceService {

	@Autowired
	private ItemPriceDAO mItemPriceDAO;



	@Override
	public ItemPrice findItemPriceById(String pProductId) {
		Optional<ItemPrice> optionalItemPrice = mItemPriceDAO.findById(pProductId);
		if (optionalItemPrice.isPresent()) {
			return optionalItemPrice.get();
		}
		return null;
	}



	@Override
	public void initData() {
		mItemPriceDAO.insertItemPrice();
	}

}
