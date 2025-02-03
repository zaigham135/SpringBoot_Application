package org.jsp.bellcurve.employeecontroller;

import java.util.List;
import java.util.Map;

import org.jsp.bellcurve.Employee;
import org.jsp.bellcurve.RatingCategory;
import org.jsp.bellcurve.employeerepo.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://bellcurve-l579kcadc-zaigham-s-projects.vercel.app/") // This website will work only after running the SpringBoot Application.
//@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    
    // Create Employee
    @PostMapping("/save")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    // Update Employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDetails));
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    // Fetch All Employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    
    
    // Create RatingCategory
    @PostMapping("/rating-categories")
    public ResponseEntity<RatingCategory> createRatingCategory(@RequestBody RatingCategory ratingCategory) {
        return ResponseEntity.ok(employeeService.createRatingCategory(ratingCategory));
    }

    // Update RatingCategory
    @PutMapping("/rating-categories/{category}")
    public ResponseEntity<RatingCategory> updateRatingCategory(
            @PathVariable String category, @RequestBody RatingCategory ratingCategoryDetails) {
        return ResponseEntity.ok(employeeService.updateRatingCategory(category, ratingCategoryDetails));
    }

    // Delete RatingCategory
    @DeleteMapping("/rating-categories/{category}")
    public ResponseEntity<Void> deleteRatingCategory(@PathVariable String category) {
        employeeService.deleteRatingCategory(category);
        return ResponseEntity.noContent().build();
    }

    // Fetch All RatingCategories
    @GetMapping("/rating-categories")
    public ResponseEntity<List<RatingCategory>> getAllRatingCategories() {
        return ResponseEntity.ok(employeeService.getAllRatingCategories());
    }
    
    

    @GetMapping("/actual-percentages")
    public ResponseEntity<Map<String, Double>> getActualPercentages() {
        return ResponseEntity.ok(employeeService.calculateActualPercentage());
    }

    @GetMapping("/deviation")
    public ResponseEntity<Map<String, Double>> getDeviation() {
        return ResponseEntity.ok(employeeService.computeDeviation());
    }

    @GetMapping("/suggest-adjustments")
    public ResponseEntity<List<Employee>> suggestAdjustments() {
        return ResponseEntity.ok(employeeService.suggestRatingAdjustments(employeeService.computeDeviation()));
    }
    @GetMapping("/test-ratings")
    public void testRatings() {
        employeeService.testGetRatingCounts();
    }
    
    


}

