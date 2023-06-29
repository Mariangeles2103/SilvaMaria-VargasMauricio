package com.backend.integradorSilvaVargas.Service;

import com.backend.integradorSilvaVargas.dto.TurnoDto;
import com.backend.integradorSilvaVargas.entity.Turno;
import com.backend.integradorSilvaVargas.exception.BadRequestException;
import com.backend.integradorSilvaVargas.exception.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    TurnoDto registrarTurno(Turno turno) throws BadRequestException;

    List<TurnoDto> listarTodos();

    TurnoDto buscarTurnoPorId(Long id) throws ResourceNotFoundException;

    TurnoDto actualizarTurno(Turno turno) throws BadRequestException;

    void eliminarTurno(Long id) throws ResourceNotFoundException;
}
