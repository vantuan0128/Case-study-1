package com.tun.casestudy1.controller;

import com.tun.casestudy1.dto.DepartmentAchievementDto;
import com.tun.casestudy1.dto.EmployeeAchievementDto;
import com.tun.casestudy1.dto.ExcellentEmployeeDto;
import com.tun.casestudy1.entity.Department;
import com.tun.casestudy1.entity.Employee;
import com.tun.casestudy1.entity.EmployeeRecord;
import com.tun.casestudy1.service.DepartmentService;
import com.tun.casestudy1.service.EmployeeRecordService;
import com.tun.casestudy1.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AdminController {

    EmployeeService employeeService;
    DepartmentService departmentService;
    EmployeeRecordService employeeRecordService;

    // Get View

    @GetMapping("/adminHome")
    public String adminHomePage(Model model) {
        List<ExcellentEmployeeDto> excellentEmployeeDtos = employeeRecordService.findExcellentEmployees();
        model.addAttribute("excellentEmployees", excellentEmployeeDtos);
        return "admin/adminHome";
    }

    @GetMapping("/employee-management")
    public String getEmployeeManagementPage(Model model)  {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
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

    @GetMapping("/view-people-achievements")
    public String getPeopleAchievements(Model model) {
        List<EmployeeAchievementDto> listAchievements = employeeRecordService.findAndCountByEmployeeId();
        model.addAttribute("listAchievements", listAchievements);
        return "admin/view-people-achievements";
    }

    @GetMapping("/view-departments-achievements")
    public String getDepartmentsAchievements(Model model) {
        List<DepartmentAchievementDto> listAchievements = employeeRecordService.findAndCountByDepartmentId();
        model.addAttribute("listAchievements", listAchievements);
        return "admin/view-departments-achievements";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // Get Add View
    @GetMapping("/add-department")
    public String getAddDepartmentPage() {
        return "admin/add-department";
    }

    @GetMapping("/add-employee")
    public String getAddEmployeePage(Model model){
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "admin/add-employee";
    }

    @GetMapping("/create-achievement")
    public String getAddAchievementPage(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "admin/add-achievement";
    }

    // Get Edit View

    @GetMapping("/edit-account/{id}")
    public String getEditAccountPage(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.find(id);
        model.addAttribute("employee", employee);
        return "admin/edit-account";
    }

    @GetMapping("/edit-department/{id}")
    public String getEditDepartmentPage(@PathVariable("id") int id, Model model) {
        Department department = departmentService.find(id);
        model.addAttribute("department", department);
        return "admin/edit-department";
    }

    @GetMapping("/edit-employee/{id}")
    public String getEditEmployeePage(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.find(id);
        model.addAttribute("employee", employee);
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "admin/edit-employee";
    }

    // Save
    @PostMapping("/add-employee")
    public String addEmployee(@ModelAttribute Employee employee) {
         employeeService.save(employee);
         return "redirect:/admin/employee-management";
    }

    @PostMapping("/add-department")
    public String addDepartment(@RequestParam("name") String name) {
        Department department = new Department();
        department.setName(name);
        departmentService.save(department);
        return "redirect:/admin/department-management";
    }

    @PostMapping("add-achievement")
    public String addAchivement(@ModelAttribute EmployeeRecord employeeRecord) {
        employeeRecordService.save(employeeRecord);
        return "redirect:/admin/employee-management";
    }

    // Update save

    @PostMapping("/edit-account")
    public String updateAccount(@RequestParam("id") int id,
                               @ModelAttribute Employee employee) {
        employeeService.updateAccount(id, employee);
        return "redirect:/admin/account-management";
    }

    @PostMapping("/edit-department")
    public String updateDepartment(@RequestParam("id") int id,
                                   @RequestParam("name") String name) {
        departmentService.update(id, name);
        return "redirect:/admin/department-management";
    }

    @PostMapping("/edit-employee")
    public String updateEmployee(@RequestParam("id") int id,
                                 @ModelAttribute Employee employee) {
        employeeService.updateEmployee(id, employee);
        return "redirect:/admin/employee-management";
    }

    // Delete

    @GetMapping("/delete-account/{id}")
    public String deleteAccount(@PathVariable("id") int id) {
        employeeService.delete(id);
        return "redirect:/admin/account-management";
    }


    @GetMapping("/delete-department/{id}")
    public String deleteDepartment(@PathVariable("id") int id) {
        departmentService.delete(id);
        return "redirect:/admin/department-management";
    }


    @GetMapping("/delete-employee/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        employeeService.delete(id);
        return "redirect:/admin/employee-management";
    }

    // Search employee
    @GetMapping("/search")
    public String searchEmployees(@RequestParam String query, Model model) {
        List<Employee> employees = employeeService.searchUser(query);
        model.addAttribute("employees", employees.isEmpty() ? new ArrayList<>() : employees);
        return "fragments/employee-fragment";
    }

}
