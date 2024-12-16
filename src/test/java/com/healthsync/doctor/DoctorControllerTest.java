//package com.healthsync.doctor;
//
//import com.healthsync.doctor.controller.DoctorController;
//import com.healthsync.doctor.model.Doctor;
//import com.healthsync.doctor.service.DoctorService;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//public class DoctorControllerTest {
//    private final DoctorService doctorService = Mockito.mock(DoctorService.class);
//    private final DoctorController doctorController = new DoctorController(doctorService);
//
//    @Test
//    void createDoctor() {
//        Doctor doctor = new Doctor("1", "John Doe", "Male", "123456789", "john.doe@example.com", "Cardiologist", "Main Hospital", "9 AM - 5 PM");
//        when(doctorService.createDoctor(doctor)).thenReturn(doctor);
//
//        ResponseEntity<Doctor> response = doctorController.createDoctor(doctor);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(doctor, response.getBody());
//    }
//
//    @Test
//    void getAllDoctors() {
//        List<Doctor> doctors = Arrays.asList(
//                new Doctor("1", "John Doe", "Male", "123456789", "john.doe@example.com", "Cardiologist", "Main Hospital", "9 AM - 5 PM"),
//                new Doctor("2", "Jane Doe", "Female", "987654321", "jane.doe@example.com", "Surgeon", "West Hospital", "9 AM - 5 PM")
//        );
//        when(doctorService.getAllDoctors()).thenReturn(doctors);
//
//        List<Doctor> result = doctorController.getAllDoctors();
//
//        assertEquals(doctors, result);
//    }
//
//    @Test
//    void getDoctorById() {
//        Doctor doctor = new Doctor("1", "John Doe", "Male", "123456789", "john.doe@example.com", "Cardiologist", "Main Hospital", "9 AM - 5 PM");
//        when(doctorService.getDoctorById("1")).thenReturn(Optional.of(doctor));
//
//        ResponseEntity<Doctor> response = doctorController.getDoctorById("1");
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(doctor, response.getBody());
//    }
//
//    @Test
//    void getDoctorById_NotFound() {
//        when(doctorService.getDoctorById("999")).thenReturn(Optional.empty());
//
//        ResponseEntity<Doctor> response = doctorController.getDoctorById("999");
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    void updateDoctor() {
//        Doctor updatedDoctor = new Doctor("1", "John Doe", "Male", "987654321", "john.doe@newmail.com", "Surgeon", "Main Hospital", "9 AM - 5 PM");
//        when(doctorService.updateDoctor("1", updatedDoctor)).thenReturn(updatedDoctor);
//
//        ResponseEntity<Doctor> response = doctorController.updateDoctor("1", updatedDoctor);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(updatedDoctor, response.getBody());
//    }
//
//    @Test
//    void deleteDoctor() {
//        Mockito.doNothing().when(doctorService).deleteDoctor("1");
//
//        ResponseEntity<Void> response = doctorController.deleteDoctor("1");
//
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//    }
//}
