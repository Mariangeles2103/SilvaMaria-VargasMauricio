package com.backend.integradorSilvaVargas.Service.impl;

import com.backend.integradorSilvaVargas.Service.ITurnoService;
import com.backend.integradorSilvaVargas.dto.OdontologoDto;
import com.backend.integradorSilvaVargas.dto.PacienteDto;
import com.backend.integradorSilvaVargas.dto.TurnoDto;
import com.backend.integradorSilvaVargas.entity.Turno;
import com.backend.integradorSilvaVargas.exception.BadRequestException;
import com.backend.integradorSilvaVargas.exception.ResourceNotFoundException;
import com.backend.integradorSilvaVargas.repository.OdontologoRepository;
import com.backend.integradorSilvaVargas.repository.PacienteRepository;
import com.backend.integradorSilvaVargas.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final PacienteRepository pacienteRepository;
    private final OdontologoRepository odontologoRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public TurnoService(
            TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService,
            PacienteRepository pacienteRepository, OdontologoRepository odontologoRepository, ObjectMapper objectMapper
    ) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public TurnoDto registrarTurno(Turno turno) throws BadRequestException {
        TurnoDto turnoDto;
        boolean coincidenciaPaciente = pacienteRepository.existsById(turno.getPaciente().getId());
        boolean coincidenciaOdontologo = odontologoRepository.existsById(turno.getOdontologo().getId());
        if (!coincidenciaPaciente || !coincidenciaOdontologo) {
            if (!coincidenciaPaciente && !coincidenciaOdontologo) {
                LOGGER.error("🛑 El paciente solicitado y el odontólogo solicitado no están registrados en la "
                        .concat("base de datos."));
                throw new BadRequestException("💀 ¡Error grave! Ni el paciente y ni el odontólogo con los que quieres "
                        .concat("agendar tu turno están registralos en nuestra base de datos, regístralos y ")
                        .concat("vuelve de nuevo a este módulo."));
            } else if (!coincidenciaPaciente) {
                LOGGER.error("🛑 El paciente solicitado no está registrado en la base de datos.");
                throw new BadRequestException("🥺 El paciente al que le estás intentando agendar un turno no se "
                        .concat("encuentra registrado en nuestra base de datos, no puedes dejar al odontólogo ")
                        .concat("solito ¡Le daría frío!"));
            } else {
                LOGGER.error("🛑 El odontólogo solicitado no está registrado en la base de datos.");
                throw new BadRequestException("🥺 El odontólogo al que le estás intentando agendar un turno no se "
                        .concat("encuentra registrado en nuestra base de datos, no puedes dejar al paciente ")
                        .concat("solito ¡Eso no es ético y profesional!"));
            }
        } else {
            turnoDto = TurnoDto.fromTurno(turnoRepository.save(turno));
            LOGGER.info("️🎫 Se registró correctamente al turno: {}.", turnoDto);
        }
        return turnoDto;
    }

    @Override
    public List<TurnoDto> listarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoDto> turnoDtos = turnos.stream()
                .map(turno -> {
                    TurnoDto turnoDto = objectMapper.convertValue(turno, TurnoDto.class);
                    turnoDto.setPaciente(PacienteDto.fromPaciente(turno.getPaciente()));
                    turnoDto.setOdontologo(OdontologoDto.fromOdontologo(turno.getOdontologo()));
                    return turnoDto;
                })
                .toList();
        LOGGER.info("Lista de todos los turnos: {}", turnoDtos);
        return turnoDtos;
    }

    @Override
    public TurnoDto buscarTurnoPorId(Long id) throws ResourceNotFoundException {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoDto turnoDto = null;
        if (turnoBuscado != null) {
            turnoDto = objectMapper.convertValue(turnoBuscado, TurnoDto.class);
            turnoDto.setPaciente(PacienteDto.fromPaciente(turnoBuscado.getPaciente()));
            turnoDto.setOdontologo(OdontologoDto.fromOdontologo(turnoBuscado.getOdontologo()));
            LOGGER.info("Se ha encontrado al turno con ID {}: {}", id, turnoDto);
        } else {
            LOGGER.info("El turno con ID {} no está registrado en la base de datos", id);
            throw new ResourceNotFoundException("El turno igresado no existe en la base de datos");
        }
        return turnoDto;
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) throws BadRequestException {
        Turno turnoAActualizar = turnoRepository.findById(turno.getId()).orElse(null);
        TurnoDto turnoActualizadoDto = null;
        if (turnoAActualizar != null) {
            turnoAActualizar = turno;
            turnoActualizadoDto = objectMapper.convertValue(turnoAActualizar, TurnoDto.class);
            LOGGER.warn("El turno con ID {} ha sido actualizado: {}", turnoAActualizar.getId(), turnoActualizadoDto);
        } else {
            LOGGER.warn("No es posible actualizar el turno porque no está registrado en la base de datos");
            throw new BadRequestException("El turno no existe en la base de datos");
        }
        return turnoActualizadoDto;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (buscarTurnoPorId(id) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con ID: {}", id);
        } else {
            LOGGER.error("El turno con ID: " + id + "no se encuentra en la base de datos");
            throw new ResourceNotFoundException("El turno con ID: " + id + "no se encuentra en la base de datos");
        }
    }
}
