package com.backend.integradorMaria.Service;

import com.backend.integradorMaria.dto.PacienteDto;
import com.backend.integradorMaria.entity.Paciente;
import com.backend.integradorMaria.exception.BadRequestException;
import com.backend.integradorMaria.exception.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {
    List<PacienteDto> listarPacientes();

    PacienteDto buscarPacientePorId(Long id) throws ResourceNotFoundException;

    PacienteDto registrarPaciente(Paciente paciente) throws BadRequestException;

    PacienteDto actualizarPaciente(Paciente paciente) throws BadRequestException;

    void eliminarPaciente(Long id) throws ResourceNotFoundException;
}
