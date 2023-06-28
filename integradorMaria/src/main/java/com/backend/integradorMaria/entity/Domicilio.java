package com.backend.integradorMaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Table(name = "DOMICILIOS")
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    private Long id;

    @Size(min = 2, max = 50, message = "Verifica tu calle")
    @NotBlank(message = "Debe contener un valor")
    @NonNull
    private String calle;

    @NotNull(message = "El valor no puede ser nulo")
    @NonNull
    private Integer numero;

    @Size(min = 2, max = 50, message = "Verifica tu localidad")
    @NotBlank(message = "Debe contener un valor")
    @NonNull
    private String localidad;

    @Size(min = 2, max = 50, message = "Verifica tu departamento")
    @NotNull(message = "El valor no puede ser nulo")
    @NonNull
    private String departamento;
}
