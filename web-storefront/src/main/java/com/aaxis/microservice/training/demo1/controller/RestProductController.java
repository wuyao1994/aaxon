package com.aaxis.microservice.training.demo1.controller;

import com.aaxis.microservice.training.demo1.domain.Product;
import com.aaxis.microservice.training.demo1.domain.ProductResult;
import com.aaxis.microservice.training.demo1.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/product")
public class RestProductController {
    private final static Logger LOGGER = LoggerFactory.getLogger(RestProductController.class);
    @Autowired
    private ProductService mProductService;



    @GetMapping("/search")
    public ProductResult search(@RequestParam("productId") String productId, @RequestParam("name") String name,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "sortName", required = false) String sortName,
            @RequestParam(value = "sortValue", required = false) String sortValue) {
        LOGGER.info("rest search product by id:{}", productId);
        page = page == null ? 1 : page;
        Page<Product> pageProducts = mProductService.searchProducts(page, productId, name, sortName, sortValue);
        ProductResult productResult = new ProductResult();
        productResult.setPageProducts(pageProducts);
        productResult.getRequest().put("productId", productId);
        productResult.getRequest().put("name", name);
        productResult.getRequest().put("page", page);
        productResult.getRequest().put("sortName", sortName);
        productResult.getRequest().put("sortValue", sortValue);
        return productResult;
    }
}
