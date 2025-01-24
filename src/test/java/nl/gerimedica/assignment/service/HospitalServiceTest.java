package nl.gerimedica.assignment.service;

import com.sun.tools.javac.Main;
import nl.gerimedica.assignment.model.Appointment;
import nl.gerimedica.assignment.model.Patient;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = Main.class)
public class HospitalServiceTest {

    @MockitoBean
    private PatientService patientService;
    @MockitoBean
    private AppointmentService appointmentService;
    @InjectMocks
    private HospitalService hospitalService;

    @Test
    public void shouldCreatePatientAndAppointmentsCorrectly() {
        Patient patient = new Patient("name", "ssn");
        List<String> reasons = List.of("x-ray");
        List<String> dates = List.of("12-10");
        List<Appointment> appointmentList = List.of(new Appointment(reasons.get(0), dates.get(0), patient));

        when(patientService.createPatient("name", "ssn")).thenReturn(patient);
        when(appointmentService.createAppointments(patient, reasons, dates)).thenReturn(
                appointmentList);

        List<Appointment> appointments = hospitalService.bulkCreateAppointments("name", "ssn", reasons, dates);

        assertThat(appointments).isEqualTo(appointmentList);
    }
}