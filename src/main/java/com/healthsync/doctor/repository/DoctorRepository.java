package com.healthsync.doctor.repository;

import com.healthsync.doctor.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorRepository extends MongoRepository<Doctor,String> {
}
