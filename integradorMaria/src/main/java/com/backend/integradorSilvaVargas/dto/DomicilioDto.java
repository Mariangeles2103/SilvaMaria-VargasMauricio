package com.backend.integradorSilvaVargas.dto;

import com.backend.integradorSilvaVargas.entity.Domicilio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;



@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DomicilioDto {
    private Long id;
    private String calle;
    private Integer numero;
    private String localidad;
    private String departamento;

    public static DomicilioDto fromDomicilio(Domicilio domicilio){
        return new DomicilioDto(
                domicilio.getId(),
                domicilio.getCalle(),
                domicilio.getNumero(),
                domicilio.getLocalidad(),
                domicilio.getDepartamento()

        );
    }
}
