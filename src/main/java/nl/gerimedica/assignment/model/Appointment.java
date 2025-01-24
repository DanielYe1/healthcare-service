package nl.gerimedica.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Appointment {

    private String reason;
    private String date;
    private Patient patient;

}
