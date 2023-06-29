package com.backend.integradorSilvaVargas.dto;

import com.backend.integradorSilvaVargas.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PacienteDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String cedula;
    private LocalDate fechaIngreso;
    private DomicilioDto domicilio;

    public static PacienteDto fromPaciente(Paciente paciente) {
       return new PacienteDto(
               paciente.getId(),
               paciente.getNombre(),
               paciente.getApellido(),
               paciente.getCedula(),
               paciente.getFechaIngreso(),
               DomicilioDto.fromDomicilio(paciente.getDomicilio())
       );
    }
}
