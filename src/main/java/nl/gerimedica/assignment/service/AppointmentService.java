package nl.gerimedica.assignment.service;

import lombok.extern.slf4j.Slf4j;
import nl.gerimedica.assignment.mapper.AppointmentMapper;
import nl.gerimedica.assignment.model.Appointment;
import nl.gerimedica.assignment.model.Patient;
import nl.gerimedica.assignment.repository.AppointmentRepository;
import nl.gerimedica.assignment.utils.HospitalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AppointmentService {

    @Autowired
    AppointmentRepository repository;

    @Autowired
    AppointmentMapper appointmentMapper;

    public void deleteAppointmentsBySSN(String ssn) {
        repository.deleteAppointmentsByPatientSsn(ssn);
    }

    public List<Appointment> createAppointments(Patient patient, List<String> reasons,
                                                List<String> dates) {

        List<Appointment> createdAppointments = new ArrayList<>();
        int loopSize = Math.min(reasons.size(), dates.size());
        for (int i = 0; i < loopSize; i++) {
            String reason = reasons.get(i);
            String date = dates.get(i);
            Appointment appointment = new Appointment(reason, date, patient);
            createdAppointments.add(appointment);
        }

        repository.saveAll(appointmentMapper.toEntityList(createdAppointments));

        for (Appointment appt : createdAppointments) {
            log.info("Created appointment for reason: {} [Date: {}] [Patient SSN: {}]", appt.getReason(), appt.getDate(), appt.getPatient().getSsn());
        }

        HospitalUtils.recordUsage("Bulk create appointments");

        return createdAppointments;
    }

    public Optional<Appointment> findLatestAppointmentBySSN(String ssn) {
        return repository.getLatestAppointmentBySsn(ssn);
    }


    public List<Appointment> getAppointmentsByReason(String reasonKeyword) {

        List<Appointment> allAppointments = appointmentMapper.toDTOList(repository.findAll());
        List<Appointment> matched = new ArrayList<>();

        for (Appointment ap : allAppointments) {
            if (ap.getReason().contains(reasonKeyword)) {
                matched.add(ap);
            }
        }

        List<Appointment> finalList = new ArrayList<>();
        for (Appointment ap : matched) {
            if (ap.getReason().equalsIgnoreCase(reasonKeyword)) {
                finalList.add(ap);
            }
        }

        HospitalUtils utils = new HospitalUtils();
        utils.recordUsage("Get appointments by reason");

        return finalList;
    }


}
