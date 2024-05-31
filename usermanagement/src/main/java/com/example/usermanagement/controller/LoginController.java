package com.example.usermanagement.controller;

import com.example.usermanagement.form.LoginForm;
import com.example.usermanagement.service.IMenuService;
import com.example.usermanagement.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private HttpSession session;

    @Autowired
    IUserService userService;

    @Autowired
    IMenuService menuService;

    @GetMapping("/index")
    public String index(@ModelAttribute("loginForm") LoginForm loginForm){
        return "index";
    }

    @GetMapping("/menu")
    public String menu(Model model){
        if(session.getAttribute("user") == null) {
            return "redirect:/index";
        }
        System.out.println(session.getAttribute("user"));
        model.addAttribute("menu", menuService.findAll());
        return "menu";
    }

    @PostMapping("/index")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model){
        //バリデーション
        if (bindingResult.hasErrors()) {
            return "index";
        }
        var user = userService.findLogin(loginForm.getLoginId(), loginForm.getPassword());
        if (user != null) {
            session.setAttribute("user", user);
            model.addAttribute("keyword", null);
            return "redirect:/menu";
        }
        model.addAttribute("error", "IDまたはパスワードが不正です");
        return "index";
    }

    @PostMapping("/logout")
    public String logout(@ModelAttribute("loginForm") LoginForm loginForm) {
        session.invalidate();
        return "logout";
    }
}