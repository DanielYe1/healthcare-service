package nl.gerimedica.assignment.mapper;

import nl.gerimedica.assignment.model.Appointment;
import nl.gerimedica.assignment.model.Patient;
import nl.gerimedica.assignment.model.entity.AppointmentEntity;
import nl.gerimedica.assignment.model.entity.PatientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient toDTO(PatientEntity patientEntity);

    PatientEntity toEntity(Patient patient);

}
