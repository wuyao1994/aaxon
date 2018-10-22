package com.aaxis.microservice.training.demo1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Demo1ApplicationTests {
    @Autowired
    private WebApplicationContext context;
    private MockMvc               mvc;



    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }



    @Test
    public void testUserController() throws Exception {
        // regist
        mvc.perform(MockMvcRequestBuilders.post("/rest/doRegist")
                .param("username", "test")
                .param("password", "test")
                .param("role", "ROLE_USER"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // login
        mvc.perform(MockMvcRequestBuilders.post("/rest/doLogin")
                .param("username", "test")
                .param("password", "test"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testProductController() throws Exception{
        // search
        mvc.perform(MockMvcRequestBuilders.get("/rest/product/search")
                .param("productId", "A_1")
                .param("name", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
