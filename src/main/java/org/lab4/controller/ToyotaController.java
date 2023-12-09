package org.lab4.controller;


import jakarta.transaction.Transactional;
import org.lab4.model.Toyota;
import org.lab4.repository.ToyotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/toyota")
public class ToyotaController {

    @Autowired
    private ToyotaRepository toyotaRepository;

    // Отримати всі магазини
    @GetMapping("/getAllToyotas")
    public ResponseEntity<Iterable<Toyota>> getAllToyotas() {
        Iterable<Toyota> toyotas = toyotaRepository.findAll();
        return ResponseEntity.ok(toyotas);
    }

    // Отримати магазин за ідентифікатором
    @GetMapping("/{id}")
    public ResponseEntity<Toyota> getToyotaById(@PathVariable Integer id) {
        return toyotaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Зберегти новий магазин
    @PostMapping("/saveToyota")
    @Transactional
    public ResponseEntity<String> saveToyota(
            @RequestParam String name,
            @RequestParam String address
    ) {
        try {
            toyotaRepository.save(name, address);
            return ResponseEntity.ok("OK");
        }
        catch (Error e) {
            return ResponseEntity.ok(e.toString());

        }
    }

    // Змінити існуючий магазин
    @PutMapping("/updateToyota/{id}")
    public ResponseEntity<Toyota> updateToyota(@PathVariable Integer id, @RequestBody Toyota updatedToyota) {
        return toyotaRepository.findById(id)
                .map(existingToyota -> {
                    existingToyota.setName(updatedToyota.getName());
                    existingToyota.setAddress(updatedToyota.getAddress());
                    Toyota savedToyota = toyotaRepository.save(existingToyota);
                    return ResponseEntity.ok(savedToyota);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Видалити магазин за ідентифікатором
    @DeleteMapping("/deleteToyota/{id}")
    public ResponseEntity<Void> deleteToyota(@PathVariable Integer id) {
        toyotaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}