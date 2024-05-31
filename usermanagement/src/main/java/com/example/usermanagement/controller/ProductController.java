package com.example.usermanagement.controller;

import com.example.usermanagement.entity.InsertProduct;
import com.example.usermanagement.entity.Product;
import com.example.usermanagement.exception.NoSuchPostalCodeException;
import com.example.usermanagement.form.ProductForm;
import com.example.usermanagement.service.ICategoriesService;
import com.example.usermanagement.service.IMenuService;
import com.example.usermanagement.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        return "insert";
    }

//    @GetMapping("/category")
//    public String category(@ModelAttribute("ProductForm") ProductForm productForm, Model model){
//        model.addAttribute("categories", categoriesService.findAll());
//        System.out.println(categoriesService.findAll());
//        return "insert";
//    }

    @GetMapping("/category")
    public String category(){
        return "category";
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

    @GetMapping("/search")
    public String search(@RequestParam(name="keyword") String keyword, @RequestParam(name="order") int sort, Model model){
        if (keyword.isEmpty()) {
            if (sort == 0) {
                model.addAttribute("menu", menuService.findAll());
                return "redirect:/menu";
            }
            model.addAttribute("menu", menuService.findKeyword(keyword, sort));
            return "menu";
        }
        model.addAttribute("menu", menuService.findKeyword(keyword, sort));
        return "menu";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") int id, Model model){
        model.addAttribute("product", menuService.findById(id));
        return "detail";
    }

    @PostMapping("/detail/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) throws NoSuchPostalCodeException {
        try {
            productService.delete(id);
        } catch (NoSuchPostalCodeException e){
            model.addAttribute("error", "削除に失敗しました");
            return "/detail/delete/" + id;
        }
        return "redirect:/menu";
    }

    @GetMapping("/detail/update/{id}")
    public String update(@ModelAttribute("ProductForm") ProductForm productForm, @PathVariable("id") int id, Model model){
        var product = menuService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoriesService.findAll());
        productForm.setProductId(product.product_id());
        productForm.setName(product.name());
        productForm.setPrice(String.valueOf(product.price()));
        productForm.setDescription(product.description());
        productForm.setCategoryId(categoriesService.findByName(product.category_name()));
        return "updateInput";
    }

    @PostMapping("/detail/update/{id}")
    public String update(@Validated @ModelAttribute("ProductForm") ProductForm productForm, BindingResult bindingResult, @PathVariable("id") int id, Model model) throws NoSuchPostalCodeException{
        //バリデーション
        if(bindingResult.hasErrors()) {
            model.addAttribute("product", menuService.findById(id));
            model.addAttribute("categories", categoriesService.findAll());
            return "updateInput";
        }
        else{
            try {
                productService.update(new Product(id, productForm.getProductId(), Integer.parseInt(productForm.getCategoryId()), productForm.getName(), Integer.parseInt(productForm.getPrice()), null, productForm.getDescription(), null, null));
            } catch (NoSuchPostalCodeException e){
                model.addAttribute("error", "商品IDが重複しています");
                model.addAttribute("product", menuService.findById(id));
                model.addAttribute("categories", categoriesService.findAll());
                return "updateInput";
            }
            return "redirect:/menu";
        }
    }
}