package com.healthsync.doctor;

import com.healthsync.doctor.model.Doctor;
import com.healthsync.doctor.repository.DoctorRepository;
import com.healthsync.doctor.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DoctorServiceTest {
    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

//    public DoctorServiceTest() {
//        this.doctorService = new DoctorService(doctorRepository);
//    }
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testcreateDoctor() {
        Doctor doctor = new Doctor("1", "John Doe", "Male", "123456789", "john.doe@example.com", "Cardiologist", "Main Hospital", "9 AM - 5 PM");
        Mockito.when(doctorRepository.save(doctor)).thenReturn(doctor);

        Doctor createdDoctor = doctorService.createDoctor(doctor);

        assertNotNull(createdDoctor);
        assertEquals(doctor, createdDoctor);
        verify(doctorRepository, times(1)).save(any(Doctor.class));

    }

    @Test
    void getDoctorById() {
        Doctor doctor = new Doctor("1", "John Doe", "Male", "123456789", "john.doe@example.com", "Cardiologist", "Main Hospital", "9 AM - 5 PM");
        Mockito.when(doctorRepository.findById("1")).thenReturn(Optional.of(doctor));

        Optional<Doctor> foundDoctor = doctorService.getDoctorById("1");

        assertEquals(Optional.of(doctor), foundDoctor);
    }

    @Test
    void getDoctorById_NotFound() {
        Mockito.when(doctorRepository.findById("999")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            doctorService.getDoctorById("999").orElseThrow(() -> new RuntimeException("Doctor not found"));
        });
    }

    @Test
    void updateDoctor() {
        Doctor updatedDoctor = new Doctor("1", "John Doe", "Male", "987654321", "john.doe@newmail.com", "Surgeon", "Main Hospital", "9 AM - 5 PM");
        Mockito.when(doctorRepository.findById("1")).thenReturn(Optional.of(updatedDoctor));
        Mockito.when(doctorRepository.save(updatedDoctor)).thenReturn(updatedDoctor);

        Doctor result = doctorService.updateDoctor("1", updatedDoctor);

        assertEquals(updatedDoctor, result);
    }

    @Test
    void updateDoctor_NotFound() {
        Doctor updatedDoctor = new Doctor("999", "Jane Doe", "Female", "987654321", "jane.doe@newmail.com", "Orthopedist", "West Hospital", "9 AM - 5 PM");
        Mockito.when(doctorRepository.findById("999")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            doctorService.updateDoctor("999", updatedDoctor);
        });
    }

    @Test
    void deleteDoctor() {
        Mockito.doNothing().when(doctorRepository).deleteById("1");

        doctorService.deleteDoctor("1");

        Mockito.verify(doctorRepository, Mockito.times(1)).deleteById("1");
    }
}
