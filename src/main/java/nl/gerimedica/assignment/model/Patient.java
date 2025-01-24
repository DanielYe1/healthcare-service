package nl.gerimedica.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Patient {

    private Long id;
    private String name;
    private String ssn;
    private List<Appointment> appointments;

    public Patient(String name, String ssn) {
        this.name = name;
        this.ssn = ssn;
    }
}
