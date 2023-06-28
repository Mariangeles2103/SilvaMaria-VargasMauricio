package com.backend.integradorMaria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "PACIENTES")
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    private Long id;

    @Size(min = 2, max = 50, message = "Verifica tu nombre")
    @NotBlank(message = "No puede ser nulo y debe contener un valor")
    @NonNull
    private String nombre;

    @Size(min = 2, max = 50, message = "Verifica tu apellido")
    @NotBlank(message = "No puede ser nulo y debe contener un valor")
    @NonNull
    private String apellido;

    @Size(min = 2, max = 50, message = "Verifica tu cedula")
    @NotBlank(message = "No puede ser nulo y debe contener un valor")
    @NonNull
    private String cedula;

    @NotNull(message = "No puede ser nulo")
    @NotBlank(message = "Debe contener un valor")
    @FutureOrPresent(message = "Escribe una fecha a partir de hoy")
    @NonNull
    private LocalDate fechaIngreso;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id")
    @NotNull(message = "No puede ser nulo")
    @NonNull
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turnos;
}
