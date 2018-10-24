# AAXON

## Required
1) Need install MySql database. schema name="test", username="root", password="password"
2) This program need 8090,8081,8082 ports
3) JDK8

## How to running
### Run price service module
        
        git clone https://stash.aaxisgroup.net/users/scorityzhu/repos/simpletraining1priceservice/browse the repository.
        create your own branch base on "master" branch and use your name as branch name.
        pull your branch and use "mvn spring-boot:run" to start it.

### Run inventory service module

        git clone https://stash.aaxisgroup.net/users/scorityzhu/repos/simpletraining1inventoryservice/browse
        create your own branch base on "master" branch and use your name as branch name.
        pull your branch and use "mvn spring-boot:run" to start it.

### Run this web module

        git clone https://stash.aaxisgroup.net/users/scorityzhu/repos/simpletraining1web/browse 
        create your own branch base on "master" branch and use your name as branch name.
        pull your branch and use "mvn spring-boot:run" to start it.

### Inital temporary data

        If we use above free installation version Mysql. we do not need initial data.
        if we use ourself's mysql, we need initail data with blow url.  
        http://localhost:8080/category/initData


### Access
        http://localhost:8080/login

 


Training report
=====================================================
        Input all what you changed content blow.

## Mandatory items

### JDK8 Lambda Expression
        
        down
        
### Spring Validation
        
        validate registor input param
        
### Springboot Unit Testing
        
        write unit test for registor/login/search

### Logging
        
        log4j
        
### Spring Security
        
        use spring security for login
        
### Spring Data JPA - MySQL
        
        use jpa mysql
        
### Spring Data Redis
        
        use redis cache
        
### Springboot Application Performance Tuning
        
        add index:
        ALTER TABLE product ADD INDEX category_priority (category_id, priority);
        ALTER TABLE product ADD INDEX category_date (category_id, created_date);
        ALTER TABLE product ADD INDEX category_id (category_id, id);
## Optional items

        1.ELK+spring cloud sleuth
        2.spring cloud bus+config/ribbon/hystrix/eureka