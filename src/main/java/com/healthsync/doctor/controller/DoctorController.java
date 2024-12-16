package com.healthsync.doctor.controller;

import com.healthsync.doctor.model.Doctor;
import com.healthsync.doctor.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor){
        return ResponseEntity.ok(doctorService.createDoctor(doctor));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable String id){
        return doctorService.getDoctorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable String id, @RequestBody Doctor updatedDoctor) {
        try {
            return ResponseEntity.ok(doctorService.updateDoctor(id, updatedDoctor));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable String id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }


}
