package com.backend.integradorMaria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "ODONTOLOGOS")
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    private Long id;

    @Size(max = 15)
    @Pattern(regexp = "^\\d+$", message = "La matrícula solo puede contener números")
    @NonNull
    private String matricula;

    @Size(max = 50, message = "El nombre puede tener hasta 50 caracteres")
    @NotBlank(message = "No puede ser nulo y debe contener un valor")
    @NonNull
    private String nombre;

    @Size(max = 50, message = "El apellido puede tener hasta 50 caracteres")
    @NotBlank(message = "No puede ser nulo y debe contener un valor")
    @NonNull
    private String apellido;

    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turnos;
}
