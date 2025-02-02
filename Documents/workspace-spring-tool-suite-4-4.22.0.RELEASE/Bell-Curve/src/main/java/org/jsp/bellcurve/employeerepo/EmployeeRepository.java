package org.jsp.bellcurve.employeerepo;

import java.util.List;

import org.jsp.bellcurve.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // This query groups by the letter rating and counts the number of employees in each group.
    @Query("SELECT e.rating, COUNT(e) FROM Employee e GROUP BY e.rating")
    List<Object[]> getRatingCounts();

    // This method is useful for fetching employees by their letter rating.
    @Query("SELECT e FROM Employee e WHERE e.rating IN :ratings")
    List<Employee> findByRatings(@Param("ratings") List<String> ratings);
}
