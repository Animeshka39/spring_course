package org.chdtu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@ComponentScan(basePackages = "org.chdtu")
public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Customer customer = context.getBean(Customer.class);

        List<Car> cars = customer.getCompany().getCars();
        System.out.println("\nCustomer Name: " + customer.getName());
        System.out.println("Company Name: " + customer.getCompany().getName());
        System.out.println("Cars:");
        for (Car car : cars) {
            System.out.println("Car Name: " + car.getName());
            System.out.println("Car Price: " + car.getPrice());
            if (car instanceof Toyota) {
                Toyota toyota = (Toyota) car;
                System.out.println("Brand: " + toyota.getBrand());
            }
            if (car instanceof Subaru) {
                Subaru subaru = (Subaru) car;
                System.out.println("Brand: " + subaru.getBrand());
            }
            System.out.println("------------------");
        }
    }
}
