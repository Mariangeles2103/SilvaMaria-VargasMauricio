package com.backend.integradorSilvaVargas.Service;

import com.backend.integradorSilvaVargas.dto.PacienteDto;
import com.backend.integradorSilvaVargas.entity.Paciente;
import com.backend.integradorSilvaVargas.exception.BadRequestException;
import com.backend.integradorSilvaVargas.exception.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {
    List<PacienteDto> listarPacientes();

    PacienteDto buscarPacientePorId(Long id) throws ResourceNotFoundException;

    PacienteDto registrarPaciente(Paciente paciente) throws BadRequestException;

    PacienteDto actualizarPaciente(Paciente paciente) throws BadRequestException;

    void eliminarPaciente(Long id) throws ResourceNotFoundException;
}
