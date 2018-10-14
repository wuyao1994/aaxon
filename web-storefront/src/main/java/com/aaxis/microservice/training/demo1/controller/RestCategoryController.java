package com.aaxis.microservice.training.demo1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaxis.microservice.training.demo1.domain.Product;
import com.aaxis.microservice.training.demo1.domain.ProductResult;
import com.aaxis.microservice.training.demo1.service.CategoryService;
import com.aaxis.microservice.training.demo1.service.ProductService;

@RestController
@RequestMapping("/rest/category")
public class RestCategoryController {
	private final static Logger	LOGGER	= LoggerFactory.getLogger(RestCategoryController.class);
	@Autowired
	private CategoryService		mCategoryService;

	@Autowired
	private ProductService		mProductService;



	@GetMapping("/initData")
	public String initData() {
		mCategoryService.initData();
		return "redirect:/login";
	}



	@GetMapping(value = { "/{categoryId}/{page}", "/{categoryId}/{page}/{sortName}/{sortValue}" })
	public ProductResult restFindProductsByCategory(@PathVariable("categoryId") String categoryId,
			@PathVariable(name = "page", required = false) String page,
			@PathVariable(name = "sortName", required = false) String sortName,
			@PathVariable(name = "sortValue", required = false) String sortValue) {
		LOGGER.info("rest find Products by category id:{}", categoryId);
		Page<Product> pageProducts = mProductService.findProductsInPLP(categoryId, Integer.parseInt(page), sortName,
				sortValue);
		ProductResult productResult = new ProductResult();
		productResult.setPageProducts(pageProducts);
		productResult.getRequest().put("requestCategoryId", categoryId);
		productResult.getRequest().put("page", page);
		productResult.getRequest().put("sortName", sortName);
		productResult.getRequest().put("sortValue", sortValue);
		return productResult;
	}

}
