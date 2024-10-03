package com.tun.casestudy1.controller;

import com.tun.casestudy1.dto.response.DepartmentAchievementDto;
import com.tun.casestudy1.dto.response.EmployeeAchievementDto;
import com.tun.casestudy1.dto.response.ExcellentEmployeeDto;
import com.tun.casestudy1.dto.request.UpdateAccountDto;
import com.tun.casestudy1.dto.request.UpdateEmployeeDto;
import com.tun.casestudy1.entity.Department;
import com.tun.casestudy1.entity.Employee;
import com.tun.casestudy1.entity.EmployeeRecord;
import com.tun.casestudy1.service.DepartmentService;
import com.tun.casestudy1.service.EmployeeRecordService;
import com.tun.casestudy1.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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
        model.addAttribute("type", "Homepage");
        return "admin/view-list";
    }

    @GetMapping("/employee-management")
    public String getEmployeeManagementPage(Model model)  {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        model.addAttribute("type", "Employee");
        return "admin/view-list";
    }

    @GetMapping("/department-management")
    public String getDepartmentManagementPage(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        model.addAttribute("type", "Department");
        return "admin/view-list";
    }

    @GetMapping("/account-management")
    public String getAccountManagementPage(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        model.addAttribute("type", "Account");
        return "admin/view-list";
    }

    @GetMapping("/view-people-achievements")
    public String getPeopleAchievements(Model model) {
        List<EmployeeAchievementDto> listAchievements = employeeRecordService.findAndCountByEmployeeId();
        model.addAttribute("listAchievements", listAchievements);
        model.addAttribute("type", "Employee-achievement");
        return "admin/view-list";
    }

    @GetMapping("/view-departments-achievements")
    public String getDepartmentsAchievements(Model model) {
        List<DepartmentAchievementDto> listAchievements = employeeRecordService.findAndCountByDepartmentId();
        model.addAttribute("listAchievements", listAchievements);
        model.addAttribute("type", "Department-achievement");
        return "admin/view-list";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // Get Add View
    @GetMapping("/add-department")
    public String getAddDepartmentPage(Model model) {
        model.addAttribute("type", "Department");
        return "admin/add";
    }

    @GetMapping("/add-employee")
    public String getAddEmployeePage(Model model){
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        model.addAttribute("type", "Employee");
        return "admin/add";
    }

    @GetMapping("/create-achievement")
    public String getAddAchievementPage(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        model.addAttribute("type", "Employee-achievement");
        return "admin/add";
    }

    // Get Edit View

    @GetMapping("/edit-account/{id}")
    public String getEditAccountPage(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.find(id);
        model.addAttribute("employee", employee);
        model.addAttribute("type", "Account");
        return "admin/edit";
    }

    @GetMapping("/edit-department/{id}")
    public String getEditDepartmentPage(@PathVariable("id") int id, Model model) {
        Department department = departmentService.find(id);
        model.addAttribute("department", department);
        model.addAttribute("type", "Department");
        return "admin/edit";
    }

    @GetMapping("/edit-employee/{id}")
    public String getEditEmployeePage(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.find(id);
        model.addAttribute("employee", employee);
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        model.addAttribute("type", "Employee");
        return "admin/edit";
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
                                @Valid @ModelAttribute UpdateAccountDto employee) {
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
                                 @Valid @ModelAttribute UpdateEmployeeDto employee) {
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
