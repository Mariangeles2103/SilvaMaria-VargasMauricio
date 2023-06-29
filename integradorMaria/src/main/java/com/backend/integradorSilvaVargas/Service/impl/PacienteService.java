package com.backend.integradorSilvaVargas.Service.impl;

import com.backend.integradorSilvaVargas.Service.IPacienteService;
import com.backend.integradorSilvaVargas.dto.DomicilioDto;
import com.backend.integradorSilvaVargas.dto.PacienteDto;
import com.backend.integradorSilvaVargas.entity.Paciente;
import com.backend.integradorSilvaVargas.exception.BadRequestException;
import com.backend.integradorSilvaVargas.exception.ResourceNotFoundException;
import com.backend.integradorSilvaVargas.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private final PacienteRepository pacienteRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, ObjectMapper objectMapper) {
        this.pacienteRepository = pacienteRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public List<PacienteDto> listarPacientes() {
        List<PacienteDto> pacienteDtos = pacienteRepository
                .findAll()
                .stream()
                .map(paciente -> {
                    PacienteDto pacienteDto = objectMapper.convertValue(paciente, PacienteDto.class);
                    pacienteDto.setDomicilio(DomicilioDto.fromDomicilio(paciente.getDomicilio()));
                    return pacienteDto;
                })
                .toList();
        LOGGER.info("Lista de todos los pacientes: {}", pacienteDtos);
        return pacienteDtos;
    }


    @Override
    public PacienteDto buscarPacientePorId(Long id) throws ResourceNotFoundException {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        PacienteDto pacienteDto = null;
        if (pacienteBuscado != null) {
            pacienteDto = objectMapper.convertValue(pacienteBuscado, PacienteDto.class);
            pacienteDto.setDomicilio(DomicilioDto.fromDomicilio(pacienteBuscado.getDomicilio()));
            LOGGER.info("Paciente encontrado: {}", pacienteDto);

        } else {
            LOGGER.info("El paciente no se encuentra en  nuestros registros");
            throw new ResourceNotFoundException("El paciente no se encuentra en  nuestros registros");
        }

        return pacienteDto;
    }

    @Override
    public PacienteDto registrarPaciente(Paciente paciente) throws BadRequestException {
        if (paciente.getDomicilio() == null) {
            throw new BadRequestException("El paciente no tiene un domicilio registrado");
        }

        PacienteDto pacienteDto = objectMapper.convertValue(pacienteRepository.save(paciente), PacienteDto.class);
        pacienteDto.setDomicilio(DomicilioDto.fromDomicilio(paciente.getDomicilio()));

        LOGGER.info("Nuevo paciente registrado con éxito: {}", pacienteDto);

        return pacienteDto;
    }


    @Override
    public PacienteDto actualizarPaciente(Paciente paciente) throws BadRequestException {
        Paciente pacienteAActualizar = pacienteRepository.findById(paciente.getId()).orElse(null);
        PacienteDto pacienteActualizadoDto = null;
        if (pacienteAActualizar != null) {
            pacienteAActualizar = paciente;
            pacienteActualizadoDto = registrarPaciente(pacienteAActualizar);
            LOGGER.warn("El paciente con ID {} ha sido actualizado: {}", pacienteAActualizar.getId(), pacienteActualizadoDto);
        } else {
            LOGGER.warn("No es posible actualizar el paciente porque no se encuentra en nuestros registros");
            throw new BadRequestException("El paciente no se encuentra en nuestros registros");
        }

        return pacienteActualizadoDto;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if (buscarPacientePorId(id) != null) {
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado al paciente con ID: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el paciente con id " + id);;
            throw new ResourceNotFoundException("No se ha encontrado el paciente con id " + id);
        }
    }
}
