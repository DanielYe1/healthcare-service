package nl.gerimedica.assignment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Appointment {

    private String reason;
    private String date;
    private Patient patient;

}
