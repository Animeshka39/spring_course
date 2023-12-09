package org.lab4.controller;

import jakarta.transaction.Transactional;
import org.lab4.model.Car;
import org.lab4.model.Customer;
import org.lab4.model.Toyota;
import org.lab4.repository.CarsRepository;
import org.lab4.repository.CustomerRepository;
import org.lab4.repository.ToyotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ToyotaRepository toyotaRepository;
    @Autowired
    private CarsRepository carsRepository;

    // Отримати всіх покупців
    @GetMapping("/getAllCustomers")
    public ResponseEntity<Iterable<Customer>> getAllCustomers() {
        Iterable<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }

    // Отримати покупця за ідентифікатором
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        return customerOptional
                .map(customer -> ResponseEntity.ok().body(customer))
                .orElse(ResponseEntity.notFound().build());
    }

    // Зберегти нового покупця
    @PostMapping("/saveCustomer")
    public ResponseEntity<Customer> saveCustomer(
            @RequestParam String name,
            @RequestParam Integer phone,
            @RequestParam Integer carId
    ) {
        Optional<Toyota> toyotaOptional = toyotaRepository.findById(carId);

        if (toyotaOptional.isPresent()) {
            Toyota toyota = toyotaOptional.get();
            Customer customer = new Customer();
            customer.setName(name);
            customer.setPhone(phone);
            customer.setToyota(toyota);
            Customer savedCustomer = customerRepository.save(customer);
            return ResponseEntity.ok(savedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Видалити покупця за ідентифікатором
    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        customerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addCarForCustomer/{id}")
    public ResponseEntity<Customer> addCarForCustomer(
            @PathVariable Integer id,
            @RequestParam Integer carId
    ) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(id);

        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            Car car = carsRepository.findById(carId.intValue());

            if (car != null) {
                Set<Car> cars = new HashSet<>();
                cars.add(car);
                existingCustomer.setCars(cars);
                Customer savedCustomer = customerRepository.save(existingCustomer);
                return ResponseEntity.ok(savedCustomer);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getCarsAndPricesByPhone")
    public ResponseEntity<Map<String, List<Double>>> getCarsAndPricesByPhone(@RequestParam Integer phone) {
        Customer customer = customerRepository.findByPhone(phone);
        Map<String, List<Double>> carsAndPrices = new HashMap<>();
        for (Car car : customer.getCars()) {
            List<Double> prices = carsRepository.findPriceByCarAndCustomer(car.getId(), customer.getToyota().getId())
                    .stream()
                    .map(result -> Double.valueOf(result[2].toString()))
                    .collect(Collectors.toList());
            carsAndPrices.put(car.getName(), prices);
        }

        return ResponseEntity.ok(carsAndPrices);
    }

}