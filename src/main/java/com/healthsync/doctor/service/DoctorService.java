package com.healthsync.doctor.service;

import com.healthsync.doctor.model.Doctor;
import com.healthsync.doctor.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private static final Logger log = LoggerFactory.getLogger(DoctorService.class);

    private final DoctorRepository doctorRepository;


    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor createDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public Optional<Doctor> getDoctorById(String doctorId){
        return doctorRepository.findById(doctorId);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }


    public Doctor updateDoctor(String id, Doctor updatedDoctor) {
        return doctorRepository.findById(id)
                .map(doctor -> {
                    doctor.setName(updatedDoctor.getName());
                    doctor.setGender(updatedDoctor.getGender());
                    doctor.setContactNum(updatedDoctor.getContactNum());
                    doctor.setEmail(updatedDoctor.getEmail());
                    doctor.setSpecialization(updatedDoctor.getSpecialization());
                    doctor.setWorkLocation(updatedDoctor.getWorkLocation());
                    doctor.setAvailability(updatedDoctor.getAvailability());
                    return doctorRepository.save(doctor);
                })
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public void deleteDoctor(String id){
        doctorRepository.deleteById(id);
    }
}
