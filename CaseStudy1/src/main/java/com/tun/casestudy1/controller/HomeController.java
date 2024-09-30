package com.tun.casestudy1.controller;

import com.tun.casestudy1.dto.request.LoginDto;
import com.tun.casestudy1.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class HomeController {

    AuthService authService;

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/adminHome")
    public String getAdminPage() {
        return "adminHome";
    }

    @GetMapping("/userHome")
    public String getUserPage() {
        return "userHome";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        String viewName = authService.authenticate(username, password);

        if (viewName != null) {
            return viewName;
        }

        model.addAttribute("error", "Invalid credentials");
        return "login";
    }
}
