package com.backend.integradorMaria.Service;

import com.backend.integradorMaria.dto.TurnoDto;
import com.backend.integradorMaria.entity.Turno;
import com.backend.integradorMaria.exception.BadRequestException;
import com.backend.integradorMaria.exception.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    TurnoDto registrarTurno(Turno turno) throws BadRequestException;

    List<TurnoDto> listarTodos();

    TurnoDto buscarTurnoPorId(Long id) throws ResourceNotFoundException;

    TurnoDto actualizarTurno(Turno turno) throws BadRequestException;

    void eliminarTurno(Long id) throws ResourceNotFoundException;
}
