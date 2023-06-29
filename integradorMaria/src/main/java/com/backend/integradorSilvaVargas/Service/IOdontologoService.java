package com.backend.integradorSilvaVargas.Service;

import com.backend.integradorSilvaVargas.dto.OdontologoDto;
import com.backend.integradorSilvaVargas.entity.Odontologo;
import com.backend.integradorSilvaVargas.exception.BadRequestException;
import com.backend.integradorSilvaVargas.exception.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {
    OdontologoDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException;

    List<OdontologoDto> listarOdontologos();

    OdontologoDto registrarOdontologo(Odontologo odontologo) throws BadRequestException;

    OdontologoDto actualizarOdontologo(Odontologo odontologo) throws BadRequestException;

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
}
