package com.tun.casestudy1.controller;

import com.tun.casestudy1.dto.response.ExcellentEmployeeDto;
import com.tun.casestudy1.service.EmployeeRecordService;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {

    EmployeeRecordService employeeRecordService;

    @GetMapping("/userHome")
    public String userHomePage(Model model) {
        List<ExcellentEmployeeDto> excellentEmployeeDtos = employeeRecordService.findExcellentEmployees();
        model.addAttribute("excellentEmployees", excellentEmployeeDtos);
        return "user/userHome";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
