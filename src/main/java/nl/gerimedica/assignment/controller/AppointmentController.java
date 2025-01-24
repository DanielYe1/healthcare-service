package nl.gerimedica.assignment.controller;

import nl.gerimedica.assignment.model.Appointment;
import nl.gerimedica.assignment.service.AppointmentService;
import nl.gerimedica.assignment.service.HospitalService;
import nl.gerimedica.assignment.utils.HospitalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    HospitalService hospitalService;

    @Autowired
    AppointmentService appointmentService;

    /**
     * Example: {
     * "reasons": ["Checkup", "Follow-up", "X-Ray"],
     * "dates": ["2025-02-01", "2025-02-15", "2025-03-01"]
     * }
     */
    @PostMapping("/bulk-appointments")
    public ResponseEntity<List<Appointment>> createBulkAppointments(
            @RequestParam String patientName,
            @RequestParam String ssn,
            @RequestBody Map<String, List<String>> payload
    ) {
        List<String> reasons = payload.get("reasons");
        List<String> dates = payload.get("dates");

        HospitalUtils.recordUsage("Controller triggered bulk appointments creation");

        List<Appointment> created = hospitalService.bulkCreateAppointments(patientName, ssn, reasons, dates);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/appointments-by-reason")
    public ResponseEntity<List<Appointment>> getAppointmentsByReason(@RequestParam String keyword) {
        List<Appointment> found = appointmentService.getAppointmentsByReason(keyword);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @PostMapping("/delete-appointments")
    public ResponseEntity<String> deleteAppointmentsBySSN(@RequestParam String ssn) {
        appointmentService.deleteAppointmentsBySSN(ssn);
        return new ResponseEntity<>("Deleted all appointments for SSN: " + ssn, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/appointments/latest")
    public ResponseEntity<Appointment> getLatestAppointment(@RequestParam String ssn) {
        Optional<Appointment> latest = appointmentService.findLatestAppointmentBySSN(ssn);
        if(latest.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(latest.get(), HttpStatus.OK);
    }
}
