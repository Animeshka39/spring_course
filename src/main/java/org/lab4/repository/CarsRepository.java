package org.lab4.repository;

import org.lab4.model.Car;
import org.lab4.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsRepository extends JpaRepository<Customer, Integer> {
    Car findById(int id);

    @Query(value = "SELECT * FROM cars_price pp WHERE pp.car_id = :carId AND pp.toyota_id = :toyotaId", nativeQuery = true)
    List<Object[]> findPriceByCarAndCustomer(@Param("carId") Integer carId, @Param("toyotaId") Integer toyotaId);
}
