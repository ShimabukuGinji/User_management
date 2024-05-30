package com.example.usermanagement.controller;

import com.example.usermanagement.entity.InsertProduct;
import com.example.usermanagement.exception.NoSuchPostalCodeException;
import com.example.usermanagement.form.LoginForm;
import com.example.usermanagement.form.ProductForm;
import com.example.usermanagement.form.SearchForm;
import com.example.usermanagement.service.ICategoriesService;
import com.example.usermanagement.service.IMenuService;
import com.example.usermanagement.service.IProductService;
import com.example.usermanagement.service.IUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    IMenuService menuService;

    @Autowired
    IProductService productService;

    @Autowired
    ICategoriesService categoriesService;

    @GetMapping("/insert")
    public String insert(@ModelAttribute("ProductForm") ProductForm productForm, Model model){
        model.addAttribute("categories", categoriesService.findAll());
        System.out.println(categoriesService.findAll());
        return "insert";
    }

    @PostMapping("/insert")
    public String insert(@Validated @ModelAttribute("ProductForm") ProductForm productForm, BindingResult bindingResult, Model model) throws NoSuchPostalCodeException {
        //バリデーション
        if(bindingResult.hasErrors()) {
            model.addAttribute("categories", categoriesService.findAll());
            return "insert";
        }
        else{
            try {
                productService.insert(new InsertProduct(productForm.getProductId(),productForm.getName(), Integer.parseInt(productForm.getPrice()), Integer.parseInt(productForm.getCategoryId()), productForm.getDescription()));
            } catch (NoSuchPostalCodeException e){
                model.addAttribute("error", "商品IDが重複しています");
                model.addAttribute("categories", categoriesService.findAll());
                return "insert";
            }
            return "redirect:/menu";
        }
    }

    @PostMapping("/menu")
    public String search(@ModelAttribute("SearchForm") SearchForm searchForm, Model model){
        System.out.println(searchForm.getKeyword());
        model.addAttribute("menu", menuService.findKeyword(searchForm.getKeyword()));
        System.out.println(menuService.findKeyword(searchForm.getKeyword()));
        return "menu";
    }

}