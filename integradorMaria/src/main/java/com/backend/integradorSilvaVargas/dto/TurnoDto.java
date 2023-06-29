package com.backend.integradorSilvaVargas.dto;

import com.backend.integradorSilvaVargas.entity.Turno;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TurnoDto {
    private Long id;
    private LocalDateTime fechaHora;
    private PacienteDto paciente;
    private OdontologoDto odontologo;

    public static TurnoDto fromTurno(Turno turno) {
        return new TurnoDto(
                turno.getId(),
                turno.getFechaHora(),
                PacienteDto.fromPaciente(turno.getPaciente()),
                OdontologoDto.fromOdontologo(turno.getOdontologo())
        );
    }
}
