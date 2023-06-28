package com.backend.integradorMaria.Service;

import com.backend.integradorMaria.dto.OdontologoDto;
import com.backend.integradorMaria.entity.Odontologo;
import com.backend.integradorMaria.exception.BadRequestException;
import com.backend.integradorMaria.exception.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {
    OdontologoDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException;

    List<OdontologoDto> listarOdontologos();

    OdontologoDto registrarOdontologo(Odontologo odontologo) throws BadRequestException;

    OdontologoDto actualizarOdontologo(Odontologo odontologo) throws BadRequestException;

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
}
