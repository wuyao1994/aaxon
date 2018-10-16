package com.aaxis.microservice.training.demo1.controller;

import com.aaxis.microservice.training.demo1.domain.Category;
import com.aaxis.microservice.training.demo1.domain.ProductResult;
import com.aaxis.microservice.training.demo1.service.CategoryService;
import com.aaxis.microservice.training.demo1.service.ProductService;
import com.aaxis.microservice.training.demo1.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService mProductService;

    @Autowired
    private CategoryService mCategoryService;

    @GetMapping("/initData")
    public String initData() {
        mProductService.initData();
        return "redirect:/login";
    }


    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @GetMapping("/searchPage")
    public String loadsSearchPage() {
        return "/search_page";
    }


    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @GetMapping("/search")
    public String search(HttpServletRequest request, Model model) {
        String productId = request.getParameter("productId");
        String name = request.getParameter("name");
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        String sortName = request.getParameter("sortName");
        String sortValue = request.getParameter("sortValue");
        LOGGER.info("search product by product id:{} and sortValue:{} and sortName:{}",productId,sortValue, sortName);
        ProductResult productResult = ((RestProductController) SpringUtil.getBean("restProductController"))
                .search(productId, name, page, sortName, sortValue);
        request.setAttribute("productResult", productResult);
        List<Category> allCategories = mCategoryService.findAllCategories();
        model.addAttribute("allCategories", allCategories);
        return "/search";
    }
}
