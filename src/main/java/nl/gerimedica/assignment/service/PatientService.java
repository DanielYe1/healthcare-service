package nl.gerimedica.assignment.service;

import lombok.extern.slf4j.Slf4j;
import nl.gerimedica.assignment.mapper.PatientMapper;
import nl.gerimedica.assignment.model.Patient;
import nl.gerimedica.assignment.model.entity.PatientEntity;
import nl.gerimedica.assignment.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientMapper patientMapper;

    public Patient createPatient(String patientName, String ssn) {
        Optional<Patient> patient = findPatientBySSN(ssn);

        if (patient.isEmpty()) {
            log.info("Creating new patient with SSN: {}", ssn);
            Patient newPatient = new Patient(patientName, ssn);
            savePatient(newPatient);
            return newPatient;
        }
        log.info("Existing patient found, SSN: {}", ssn);
        return patient.get();
    }

    private Optional<Patient> findPatientBySSN(String ssn) {

        return patientRepository.findPatientBySSN(ssn).map(c -> patientMapper.toDTO(c));
    }

    @Transactional
    private void savePatient(Patient patient) {
        PatientEntity entity = patientMapper.toEntity(patient);
        patientRepository.save(entity);
    }
}
