package nl.gerimedica.assignment.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String ssn;
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    public List<AppointmentEntity> appointments;

}
