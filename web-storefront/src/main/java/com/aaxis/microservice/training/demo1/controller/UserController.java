package com.aaxis.microservice.training.demo1.controller;

import com.aaxis.microservice.training.demo1.domain.Account;
import com.aaxis.microservice.training.demo1.domain.Category;
import com.aaxis.microservice.training.demo1.domain.UserCreateForm;
import com.aaxis.microservice.training.demo1.service.CategoryService;
import com.aaxis.microservice.training.demo1.service.UserService;
import com.aaxis.microservice.training.demo1.validator.AccountRegisterFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService                  pUserService;
    @Autowired
    private CategoryService              mCategoryService;
    @Autowired
    private AccountRegisterFormValidator mAccountRegisterFormValidator;



    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(mAccountRegisterFormValidator);
    }


    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        List<Category> allCategories = mCategoryService.findAllCategories();
        model.addAttribute("allCategories", allCategories);
        return "index";
    }



    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @RequestMapping("/index")
    public String index(Model model) {
        List<Category> allCategories = mCategoryService.findAllCategories();
        model.addAttribute("allCategories", allCategories);
        return "index";
    }



    @RequestMapping("/login")
    public String login() {
        return "login";
    }



    @RequestMapping("/regist")
    public String regist() {
        return "regist";
    }



    @PostMapping("/doRegist")
    public String doRegist(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult,
            Model model, HttpServletRequest request) {
        Account account = new Account();
        if (bindingResult.hasErrors()) {
            LOGGER.error("user validation falid");
            model.addAttribute("Errors", bindingResult.getAllErrors());
            return "regist";
        }

        try {
            account.setUsername(form.getUsername());
            account.setPassword(form.getPassword());
            account.setRole(form.getRole());
            pUserService.regist(account);
        } catch (RuntimeException e) {
            bindingResult.reject("username.exists", "username already exists");
            model.addAttribute("Errors", bindingResult.getAllErrors());
            return "regist";
        }

        return "redirect:/index";
    }
}
