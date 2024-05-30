package com.example.usermanagement.controller;

import com.example.usermanagement.entity.IUser;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.form.LoginForm;
import com.example.usermanagement.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private HttpSession session;

    @Autowired
    IUserService userService;

    @GetMapping("/user-management")
    public String index(@ModelAttribute("loginForm") LoginForm loginForm){
        return "index";
    }

    @PostMapping("/user-management")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult) {
        // バリデーション
        if(bindingResult.hasErrors()) {
            return "index";
        }
        if(loginForm.getLoginId().equals("user") && loginForm.getPassword().equals("password")) {
            var user = new User(1, "user", "テストユーザー");
            session.setAttribute("user", user);
            return "menu";
        }
        return "index";
    }

//    @PostMapping("/user-management")
//    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult){
//        //バリデーション
//        if (bindingResult.hasErrors()) {
//            return "index";
//        }
//        List<User> users = userService.findAll();
//        System.out.println(users);
//        if (loginForm.getLoginId().equals(users.get(0).loginId()) && loginForm.getLoginId().equals(users.get(0).password())) {
//            session.setAttribute("user", users.get(0));
//            return "menu";
//        }
//        for (var i=0; i<users.size(); i++) {
//            System.out.println(users);
//            if (loginForm.getLoginId().equals(users.get(i).loginId()) && loginForm.getPassword().equals(users.get(i).password())) {
//                System.out.println(users);
//                session.setAttribute("user", users.get(i));
//                System.out.println(users);
//                return "menu";
//            }
//        }
//        return "user-management";
//    }

    @PostMapping("/logout")
    public String logout(@ModelAttribute("loginForm") LoginForm loginForm) {
        session.invalidate();
        return "/index";
    }
}