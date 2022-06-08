package com.covid.vaccination.Implementation;

import com.covid.vaccination.Entity.Appointment;
import com.covid.vaccination.Entity.DoctorDoseGeneration;
import com.covid.vaccination.Entity.Dose1;
import com.covid.vaccination.Entity.Dose2;
import com.covid.vaccination.Repository.AppointmentRepository;
import com.covid.vaccination.Repository.Dose1Repository;
import com.covid.vaccination.Repository.Dose2Repository;
import com.covid.vaccination.Repository.DoseGenerationRepository;
import com.covid.vaccination.Service.DoseGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoseGenerationImpl implements DoseGenerationService {
    @Autowired
    public DoseGenerationRepository doseGenerationRepository;
    @Autowired
    public Dose1Repository dose1Repository;
    @Autowired
    public Dose2Repository dose2Repository;

    @Autowired
    public  AppointmentServiceImpl appointmentService;
    @Autowired
    public AppointmentRepository appointmentRepository;
    
    public String generatedDose(DoctorDoseGeneration doctorDoseGeneration) {

        Dose1 dose1 = dose1Repository.getDose1ByUser_id(doctorDoseGeneration.getUser_id());
        Dose2 dose2 = dose2Repository.getDose2ByUser_id(doctorDoseGeneration.getUser_id());
        Appointment appointment=appointmentRepository.findByUser_id(doctorDoseGeneration.getUser_id());

        if (dose1 == null && dose2 == null&&appointment!=null) {
            doseGenerationRepository.save(doctorDoseGeneration);
            appointmentRepository.delete(appointment);
            return "Dose 1 SuccessFully Vaccinated";
        }

        else if ((dose1 != null) && dose2 == null) {
            if(doctorDoseGeneration.getDose1()==null){
                doseGenerationRepository.save(doctorDoseGeneration);
                return "Dose 2 Successfully Vaccinated";
            }else {
                return "Already Dose 1 Completed";
            }
        } else if(dose1!=null&&dose2!=null){
            return "You Are Fully Vaccinated";
        }
        else
            return "Please Book Your Slot";
    }
}
