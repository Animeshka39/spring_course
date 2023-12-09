package org.lab4.model;

import jakarta.persistence.*;
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
@Table(name = "toyota")
public class Toyota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "toyota", fetch = FetchType.LAZY)
    private Set<CarPrice> carsPrices;

    public Toyota(String name, String address) {
        this.name = name;
        this.address = address;
    }

}
