package com.aaxis.microservice.training.demo1.service.impl;

import com.aaxis.microservice.training.demo1.dao.CategoryDao;
import com.aaxis.microservice.training.demo1.dao.ProductDao;
import com.aaxis.microservice.training.demo1.domain.Category;
import com.aaxis.microservice.training.demo1.domain.Product;
import com.aaxis.microservice.training.demo1.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private CategoryDao mCategoryDao;

    @Autowired
    private ProductDao mProductDao;

    @Autowired
    private Environment env;

    @Autowired
    private RestTemplate restTemplate;

    private static final int PRODUCT_BATCH_SIZE = 1000;



    @Override
    public void initData() {
        List<Category> categories = mCategoryDao.findAll();

        if (categories == null) {
            return;
        }
        int maxProductCoundInCategory = Integer.parseInt(env.getProperty("maxProductCoundInCategory"));

        String checkProductExistBeforeAdding = env.getProperty("checkProductExistBeforeAdding");

        for (Category category : categories) {

            int randomProductSize = new Random().nextInt(maxProductCoundInCategory / 2) + maxProductCoundInCategory / 2;

            List<Product> productist = new ArrayList<>(PRODUCT_BATCH_SIZE);

            for (int i = 1; i <= randomProductSize; i++) {
                String productId = category.getId() + "_" + i;
                String productName = RandomStringUtils.randomAlphanumeric(32);
                if ("true".equalsIgnoreCase(checkProductExistBeforeAdding)
                        && mProductDao.findById(productId).isPresent()) {
                    break;
                }
                Product product = new Product();
                product.setId(productId);
                product.setName(productName);
                product.setPriority(new Random().nextInt(100));
                Date date = randomDate("2010-01-01", "2018-01-01");
                product.setCreatedDate(date);
                product.setCategory(category);
                productist.add(product);

                if (productist.size() % PRODUCT_BATCH_SIZE == 0) {
                    mProductDao.saveAll(productist);
                    productist.clear();
                }
            }

            if (!productist.isEmpty()) {
                mProductDao.saveAll(productist);
                productist.clear();
            }
        }

        restTemplate.getForObject("http://price-service/price/initData", Map.class);
        restTemplate.getForObject("http://inventory-service/inventory/initData", Map.class);
    }



    @Override
    public List<Product> findProductsByCategoryId(String categoryId) {
        return mProductDao.findProductsByCategory_Id(categoryId);
    }



    @Override
    public Page<Product> findProductsInPLP(String categoryId, int page, String sortName, String sortValue) {
        long startTime = System.currentTimeMillis();
        Specification<Product> spec = (Root<Product> pRoot, CriteriaQuery<?> pCriteriaQuery,
                CriteriaBuilder pCriteriaBuilder) -> (
                pCriteriaBuilder.equal(pRoot.get("category").as(Category.class),
                        mCategoryDao.findById(categoryId).get())
        );
        Pageable pageable = null;

        if (sortName != null) {
            Sort sort = new Sort("ASC".equalsIgnoreCase(sortValue) ? QSort.Direction.ASC : QSort.Direction.DESC,
                    sortName);
            pageable = new PageRequest(page - 1, 20, sort);
        } else {
            pageable = new PageRequest(page - 1, 20);
        }

        Page<Product> pageResult = mProductDao.findAll(spec, pageable);
        addPriceAndInventory(pageResult.getContent());
        long cost = System.currentTimeMillis() - startTime;
        LOGGER.info("COST_TIME:{}", cost);
        return pageResult;
    }



    @Override
    public Page<Product> searchProducts(int page, String productId, String name, String sortName, String sortValue) {

        long startTime = System.currentTimeMillis();
        Specification<Product> spec = (Root<Product> pRoot, CriteriaQuery<?> pCriteriaQuery,
                CriteriaBuilder pCriteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (productId != null) {
                predicates.add(pCriteriaBuilder.equal(pRoot.get("id"), productId));
            }
            if (name != null) {
                predicates.add(pCriteriaBuilder.equal(pRoot.get("name"), name));
            }
            return pCriteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
        };
        Pageable pageable = null;

        if (sortName != null) {
            Sort sort = new Sort("ASC".equalsIgnoreCase(sortValue) ? QSort.Direction.ASC : QSort.Direction.DESC,
                    sortName);
            pageable = new PageRequest(page - 1, 20, sort);
        } else {
            pageable = new PageRequest(page - 1, 20);
        }

        Page<Product> pageResult = mProductDao.findAll(spec, pageable);
        addPriceAndInventory(pageResult.getContent());
        long cost = System.currentTimeMillis() - startTime;
        LOGGER.info("COST_TIME:{}", cost);
        return pageResult;
    }



    @Override
    public void addPriceAndInventory(List<Product> products) {
        products.forEach(product -> {
            product.setPrice(getProductPrice(product.getId()));
            product.setStock(getProductInventory(product.getId()));
        });
    }



    @Override
    @HystrixCommand(fallbackMethod = "")
    public double getProductPrice(String pProductId) {
        Double price = (Double) ((Map) restTemplate.getForObject("http://price-service/price/" + pProductId,
                Map.class)).get("price");
        return price;
    }



    @Override
    @HystrixCommand(fallbackMethod = "")
    public int getProductInventory(String pProductId) {
        Integer stock = (Integer) ((Map) restTemplate.getForObject("http://inventory-service/inventory/" + pProductId,
                Map.class)).get("stock");
        return stock;
    }



    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }



    private static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
