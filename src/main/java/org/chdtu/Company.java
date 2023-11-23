package org.chdtu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Company {
    private List<Car> cars;

    @Value("${company.name}")
    @Autowired
    private String name;

    @Autowired
    public Company(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }
    @Override
    public String toString() {
        return "Company name = " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}

