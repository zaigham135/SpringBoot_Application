package org.jsp.bellcurve;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RatingCategory {
    @Id
    private String category;  // A, B, C, D, E
    private double standardPercentage;

    // Default constructor (required by JPA)
    public RatingCategory() {
    }

    // Parameterized constructor
    public RatingCategory(String category, double standardPercentage) {
        this.category = category;
        this.standardPercentage = standardPercentage;
    }

    // Getters and setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getStandardPercentage() {
        return standardPercentage;
    }

    public void setStandardPercentage(double standardPercentage) {
        this.standardPercentage = standardPercentage;
    }
}