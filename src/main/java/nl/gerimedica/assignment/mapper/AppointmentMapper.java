package nl.gerimedica.assignment.mapper;

import nl.gerimedica.assignment.model.Appointment;
import nl.gerimedica.assignment.model.entity.AppointmentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    Appointment toDTO(AppointmentEntity entity);

    AppointmentEntity toEntity(Appointment appointment);

}
