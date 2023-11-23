package org.chdtu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
public class Customer {
    private String name;
    private Company company;

    @Autowired
    public Customer(Company company) {
        this.company = company;
    }
    public Customer (){}

    public Customer(String name, Company company) {
        this.name = name;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    @Value("${customer.name}")
    public void setName(String name) {
        this.name = name;
    }
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    @PostConstruct
    public void init() {
        System.out.println("--- Customer bean is being initialized.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("--- Customer bean is being destroyed.");
    }
}
