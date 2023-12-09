package org.lab4.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private Integer phone;

    @ManyToOne
    @JoinColumn(name = "toyota_id", nullable = false)
    private Toyota toyota;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_car",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private Set<Car> cars;

}