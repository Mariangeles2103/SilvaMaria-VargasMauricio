package com.backend.integradorSilvaVargas.dto;


import com.backend.integradorSilvaVargas.entity.Odontologo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OdontologoDto {
    private Long id;
    private String matricula;
    private String nombre;
    private String apellido;

    public static OdontologoDto fromOdontologo(Odontologo odontologo) {
        return new OdontologoDto(
                odontologo.getId(),
                odontologo.getMatricula(),
                odontologo.getNombre(),
                odontologo.getApellido()
        );
    }

}
