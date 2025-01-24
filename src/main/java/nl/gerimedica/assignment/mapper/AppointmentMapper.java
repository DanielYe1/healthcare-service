package nl.gerimedica.assignment.mapper;

import nl.gerimedica.assignment.model.Appointment;
import nl.gerimedica.assignment.model.entity.AppointmentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    Appointment toDTO(AppointmentEntity entity);

    List<Appointment> toDTOList(List<AppointmentEntity> entities);

    AppointmentEntity toEntity(Appointment appointment);

    List<AppointmentEntity> toEntityList(List<Appointment> appointment);

}
