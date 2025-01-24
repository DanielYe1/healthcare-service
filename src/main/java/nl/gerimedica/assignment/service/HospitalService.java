package nl.gerimedica.assignment.service;

import lombok.extern.slf4j.Slf4j;
import nl.gerimedica.assignment.model.Appointment;
import nl.gerimedica.assignment.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HospitalService {

    @Autowired
    private PatientService patientService;
    @Autowired
    private AppointmentService appointmentService;

    public List<Appointment> bulkCreateAppointments(
            String patientName,
            String ssn,
            List<String> reasons,
            List<String> dates
    ) {

        Patient patient = patientService.createPatient(patientName, ssn);

        return appointmentService.createAppointments(patient, reasons, dates);
    }
}
