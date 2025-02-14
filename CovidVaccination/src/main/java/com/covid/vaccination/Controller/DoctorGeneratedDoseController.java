package com.covid.vaccination.Controller;

import com.covid.vaccination.Entity.Appointment;
import com.covid.vaccination.Entity.DoctorDoseGeneration;
import com.covid.vaccination.Implementation.AppointmentServiceImpl;
import com.covid.vaccination.Implementation.DoseGenerationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorGeneratedDoseController {
    @Autowired
    public DoseGenerationImpl doseGenerationService;

    @Autowired
    public AppointmentServiceImpl appointmentService;
    @PostMapping("/Doctor/Dose/CreateDose")
    public ResponseEntity<String> createDose(@RequestBody DoctorDoseGeneration doctorDoseGeneration){
        return  new ResponseEntity<>(doseGenerationService.generatedDose(doctorDoseGeneration), HttpStatus.CREATED);

    }
    @GetMapping("/getAllAppointment")
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        return appointmentService.getAllAppointment();
    }
}
