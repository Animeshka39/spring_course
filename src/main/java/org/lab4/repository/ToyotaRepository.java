package org.lab4.repository;

import org.lab4.model.Toyota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToyotaRepository extends JpaRepository<Toyota, Integer> {
    @Override
    Optional<Toyota> findById(Integer integer);

    @Override
    List<Toyota> findAll();

    @Modifying
    @Query(value = "INSERT INTO toyota (name, address) VALUES (:name, :address)", nativeQuery = true)
    void save(@Param("name") String name, @Param("address") String address);

    @Override
    <S extends Toyota> S save(S shop);
}