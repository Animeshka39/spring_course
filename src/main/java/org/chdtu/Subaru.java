package org.chdtu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Subaru implements Car {
    @Value("Subaru")
    private String brand;
    @Value("WRX STI")
    private String name;
    @Value("21000.0")
    private double price;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {return brand;}

    public void setBrand(String brand) {
        this.brand = brand;
    }
}