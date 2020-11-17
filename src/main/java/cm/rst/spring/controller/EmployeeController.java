package cm.rst.spring.controller;

import cm.rst.spring.entity.Employee;
import cm.rst.spring.service.EmployeeService;
import cm.rst.spring.service.EmployeeServiceImpl;
import com.sun.istack.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }


    //display list of employee
    @GetMapping(value = {"", "/"})
    public String viewHomePage(Model model) {
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/showNewEmployeeFrom")
    public String showNewEmployeeForm(Model model ){
        //create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";

    }
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        //save employee to database
        employeeService.saveEmployee(employee);
        return "redirect:/" ;

        }
@GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") long id, Model model){

        // get employee from the service
    Employee employee = employeeService.getEmployeeById(id);

    //set employee as a model attribute to pre- populate the form
    model.addAttribute("employee",employee);
    return "update_employee";
}
@GetMapping("/deleteEmployee/{id}")
public String deleteEmployee(@PathVariable (value = "id") long id){

        // call delete employee

    this.employeeService.deleteEmployeeById(id);

    return "redirect:/" ;
}


}







