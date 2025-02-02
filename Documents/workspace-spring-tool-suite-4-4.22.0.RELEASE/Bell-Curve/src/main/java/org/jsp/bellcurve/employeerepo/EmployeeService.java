package org.jsp.bellcurve.employeerepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsp.bellcurve.Employee;
import org.jsp.bellcurve.RatingCategory;
import org.jsp.bellcurve.ratingrepo.RatingCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private RatingCategoryRepository ratingCategoryRepository;

 // Create Employee
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    
 // Update Employee
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employee.setName(employeeDetails.getName());
        employee.setRating(employeeDetails.getRating());
        return employeeRepository.save(employee);
    }

    // Delete Employee
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Fetch All Employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    
 // Create RatingCategory
    public RatingCategory createRatingCategory(RatingCategory ratingCategory) {
        return ratingCategoryRepository.save(ratingCategory);
    }

    // Update RatingCategory
    public RatingCategory updateRatingCategory(String category, RatingCategory ratingCategoryDetails) {
        RatingCategory ratingCategory = ratingCategoryRepository.findById(category)
                .orElseThrow(() -> new RuntimeException("RatingCategory not found with category: " + category));
        ratingCategory.setStandardPercentage(ratingCategoryDetails.getStandardPercentage());
        return ratingCategoryRepository.save(ratingCategory);
    }

    // Delete RatingCategory
    public void deleteRatingCategory(String category) {
        ratingCategoryRepository.deleteById(category);
    }

    // Fetch All RatingCategories
    public List<RatingCategory> getAllRatingCategories() {
        return ratingCategoryRepository.findAll();
    }
    
    
    
 // Assume a fixed standard percentage for each category.
    private static final Map<String, Double> STANDARD_PERCENTAGES = Map.of(
        "A", 10.0,
        "B", 20.0,
        "C", 40.0,
        "D", 20.0,
        "E", 10.0
    );
    
    public Map<String, Double> calculateActualPercentage() {
        // Get counts grouped by the letter rating
        List<Object[]> ratingCounts = employeeRepository.getRatingCounts();
        long totalEmployees = employeeRepository.count();

        Map<String, Double> actualPercentage = new HashMap<>();

        for (Object[] obj : ratingCounts) {
            // Since rating is already a letter, cast it directly
            String category = (String) obj[0];
            long count = (long) obj[1];

            // Calculate percentage and store/update the value for this category
            actualPercentage.put(category, (count * 100.0) / totalEmployees);
        }
        return actualPercentage;
    }

    
    
    
//    private String getCategory(int rating) {
//        if (rating == 1 || rating == 7 || rating == 13) return "A";
//        if (rating == 3 || rating == 9) return "B";
//        if (rating == 4 || rating == 6 || rating == 12 || rating == 14) return "C";
//        if (rating == 2 || rating == 8 || rating == 13) return "D";
//        return "E";
//    }
    
    public Map<String, Double> computeDeviation() {
        Map<String, Double> actualPercentage = calculateActualPercentage();
        Map<String, Double> deviation = new HashMap<>();

        for (String category : STANDARD_PERCENTAGES.keySet()) {
            double standard = STANDARD_PERCENTAGES.get(category);
            // If there is no data for a category, assume 0%
            double actual = actualPercentage.getOrDefault(category, 0.0);
            deviation.put(category, actual - standard);
        }
        return deviation;
    }
//
//    private double getStandardPercentage(String category) {
//        Map<String, Double> standardPercentages = Map.of(
//            "A", 10.0, "B", 20.0, "C", 40.0, "D", 20.0, "E", 10.0
//        );
//        return standardPercentages.getOrDefault(category, 0.0);
//    }
//    
//    private List<Integer> getRatingsForCategory(String category) {
//        return switch (category) {
//            case "A" -> List.of(1, 7, 13);
//            case "B" -> List.of(3, 9);
//            case "C" -> List.of(4, 6, 12, 14);
//            case "D" -> List.of(2, 8, 13);
//            case "E" -> List.of(5, 10, 11);
//            default -> Collections.emptyList();
//        };
//    }

    
    public List<Employee> suggestRatingAdjustments(Map<String, Double> deviation) {
        List<Employee> employeesToAdjust = new ArrayList<>();

        for (Map.Entry<String, Double> entry : deviation.entrySet()) {
            String category = entry.getKey();
            double deviationValue = entry.getValue();

            if (deviationValue > 0) {
                // Get all employees in the overrepresented category
                List<Employee> employees = employeeRepository.findByRatings(List.of(category));
                // Determine how many employees might need adjustment.
                // Here, we take a sublist limited by the deviationValue cast to an integer.
                int numToAdjust = Math.min((int) deviationValue, employees.size());
                employeesToAdjust.addAll(employees.subList(0, numToAdjust));
            }
        }
        return employeesToAdjust;
    }
    
    public void testGetRatingCounts() {
        List<Object[]> ratingCounts = employeeRepository.getRatingCounts();
        for (Object[] obj : ratingCounts) {
            System.out.println("Rating: " + obj[0] + ", Count: " + obj[1]);
        }
    }





}

