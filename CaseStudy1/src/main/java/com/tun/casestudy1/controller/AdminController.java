package com.tun.casestudy1.controller;

import com.tun.casestudy1.entity.Department;
import com.tun.casestudy1.entity.Employee;
import com.tun.casestudy1.service.DepartmentService;
import com.tun.casestudy1.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AdminController {

    EmployeeService employeeService;
    DepartmentService departmentService;

    @GetMapping("/adminHome")
    public String adminHomePage() {
        return "admin/adminHome";
    }

    @GetMapping("/employee-management")
    public String getEmployeeManagementPage()  {
        return "admin/employee-management";
    }

    @GetMapping("/department-management")
    public String getDepartmentManagementPage(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "admin/department-management";
    }

    @GetMapping("/account-management")
    public String getAccountManagementPage(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "admin/account-management";
    }

    @GetMapping("/achivement-records")
    public String getAchivementRecords() {
        return "admin/achievement-records";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/edit-account/{id}")
    public String getEditAccountPage(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.find(id);
        model.addAttribute("employee", employee);
        return "admin/edit-account";
    }

    @PostMapping("/edit-account")
    public String updateAccount(@RequestParam("id") int id,
                               @RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {
        employeeService.updateAccount(id, name, email, password);
        return "redirect:/admin/account-management";
    }

    @GetMapping("/delete-account/{id}")
    public String deleteAccount(@PathVariable("id") int id) {
        employeeService.delete(id);
        return "redirect:/admin/account-management";
    }

    @GetMapping("/edit-department/{id}")
    public String getEditDepartmentPage(@PathVariable("id") int id, Model model) {
        Department department = departmentService.find(id);
        model.addAttribute("department", department);
        return "admin/edit-department";
    }

    @PostMapping("/edit-department")
    public String updateDepartment(@RequestParam("id") int id,
                               @RequestParam("name") String name) {
        departmentService.update(id, name);
        return "redirect:/admin/department-management";
    }

    @GetMapping("/delete-department/{id}")
    public String deleteDepartment(@PathVariable("id") int id) {
        departmentService.delete(id);
        return "redirect:/admin/department-management";
    }

    @GetMapping("/edit-employee/{id}")
    public String getEditEmployeePage(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.find(id);
        model.addAttribute("employee", employee);
        return "admin/edit-employee";
    }

    @PostMapping("/edit-employee")
    public String updateEmployee(@RequestParam("id") int id,
                                   @RequestParam("name") String name) {
        employeeService.updateEmployee(id, name);
        return "redirect:/admin/employee-management";
    }

    @GetMapping("/delete-employee/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        employeeService.delete(id);
        return "redirect:/admin/employee-management";
    }
}
