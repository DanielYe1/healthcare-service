package nl.gerimedica.assignment.repository;

import nl.gerimedica.assignment.model.Appointment;
import nl.gerimedica.assignment.model.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    List<AppointmentEntity> getAppointmentsByReason(String reason);

    // custom query later
    void deleteAppointmentsByPatientSsn(String reason);

    Optional<Appointment> getLatestAppointmentBySsn(String ssn);
}
