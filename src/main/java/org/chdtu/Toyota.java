package org.chdtu;

import lombok.Getter;
import lombok.Setter;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Qualifier("toyota")
@Getter
@Setter
public class Toyota implements Car {

    @Value("${car.brand}")
    private String brand;

    @Value("Sprinter Trueno")
    private String name;

    @Value("${toyota.price}")
    private String priceStr;
    private double price;
    public double getPrice() {
        return Double.parseDouble(priceStr);
    }

    @PostConstruct
    public void init() {
        System.out.println("--- Toyota bean is being initialized.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("--- Toyota bean is being destroyed.");
    }
}
