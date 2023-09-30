package com.example.springapp.controller;

import com.example.springapp.model.Medicine;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MedicineController {
    private final Map<Integer, Medicine> medicineDatabase = new HashMap<>();
    private int nextMedicineId = 1;

    @PostMapping("/")
    public boolean addMedicine(@RequestBody Medicine medicine) {
        medicine.setId(nextMedicineId++);
        medicineDatabase.put(medicine.getId(), medicine);
        return true;
    }

    @PutMapping("/{id}")
    public Medicine updateMedicine(@PathVariable int id, @RequestBody Medicine updatedMedicine) {
        if (medicineDatabase.containsKey(id)) {
            Medicine existingMedicine = medicineDatabase.get(id);
            existingMedicine.setName(updatedMedicine.getName());
            existingMedicine.setPrice(updatedMedicine.getPrice());
            existingMedicine.setQuantity(updatedMedicine.getQuantity());
            existingMedicine.setDescription(updatedMedicine.getDescription());
            return existingMedicine;
        } else {
            return null; // Handle the case where the medicine with the given ID does not exist
        }
    }
}
